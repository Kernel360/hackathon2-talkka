package org.kernel360.busseat.schedule.service;

import java.sql.Time;
import java.util.List;

import org.kernel360.busseat.bus_location.service.BusLocationService;
import org.kernel360.busseat.openapi.dto.BusLocationApiResponse;
import org.kernel360.busseat.openapi.service.BusLocationApiService;
import org.kernel360.busseat.route.entity.RouteEntity;
import org.kernel360.busseat.user_request.dto.UserRequest;
import org.kernel360.busseat.user_request.service.UserRequestService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableScheduling
public class ScheduledTask {

	// private final RouteRepository routeRepository;
	private final BusLocationApiService busLocationApiService;
	private final BusLocationService busLocationService;
	private final UserRequestService userRequestService;

	@Scheduled(fixedRate = 180000) // 180000 밀리초 = 3분
	public void executeTasks() {
		log.info("ScheduledTask.executeTasks()");

		// get user requests;
		final List<UserRequest> userRequests = userRequestService.findAll();
		// request bus locations
		for (UserRequest userRequest : userRequests) {
			try {
				// TEST 필요함...
				// final var route = routeRepository.findById(userRequest.getRouteId()).orElseThrow();
				// if (!isBusTime(route)) {
				// 	continue;
				// }

				final BusLocationApiResponse busLocationApiResponse = busLocationApiService.request(userRequest);
				// save
				if (busLocationApiResponse.getMsgBody() != null && !busLocationApiResponse.getMsgBody().isEmpty()) {
					final var saved = busLocationService.saveResponseList(busLocationApiResponse.getMsgBody());
				} else {
					log.info("busLocationApiResponse.getMsgBody() is null or empty");
				}
			} catch (Exception e) {
				log.error("error on scheduling..", e);
			}
		}
	}

	private boolean isBusTime(RouteEntity route) {
		// HH:MM 으로 현재 시각과 버스 시간을 비교
		final var busEndTime = route.getDownLastTime();
		final var currentTime = new Time(System.currentTimeMillis()).toString();
		final var busStartTime = route.getUpFirstTime();

		final var busStartTimeInt = getTimeInt(busStartTime);
		final var currentTimeInt = getTimeInt(currentTime);
		var busEndTimeInt = getTimeInt(busEndTime);

		if (busEndTimeInt < busStartTimeInt) {
			busEndTimeInt += 24 * 60; // 종료 시각을 24시간 추가
		}
		return busStartTimeInt <= currentTimeInt && currentTimeInt <= busEndTimeInt;
	}

	private int getTimeInt(String time) {
		final var timeSplit = time.split(":");
		return Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
	}
}