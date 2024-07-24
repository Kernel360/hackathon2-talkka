package org.kernel360.busseat.schedule.service;

import java.util.List;

import org.kernel360.busseat.bus_location.service.BusLocationService;
import org.kernel360.busseat.openapi.dto.BusLocationApiResponse;
import org.kernel360.busseat.openapi.service.BusLocationApiService;
import org.kernel360.busseat.user_request.dto.UserRequestDto;
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

	private final BusLocationApiService busLocationApiService;
	private final BusLocationService busLocationService;
	private final UserRequestService userRequestService;

	@Scheduled(fixedRate = 10000) // 180000 밀리초 = 3분
	public void executeTasks() {
		log.info("ScheduledTask.executeTasks()");

		// get user requests;
		final List<UserRequestDto> userRequests = userRequestService.findAll();

		// request bus locations
		for (UserRequestDto userRequest : userRequests) {
			// create
			final BusLocationApiResponse busLocationApiResponse = busLocationApiService.request(
				busLocationApiService.getQueryParameters(userRequest),
				BusLocationApiResponse.class);
			log.info("response {} ", busLocationApiResponse.toString());

			// save
			if (busLocationApiResponse.getMsgBody() != null && !busLocationApiResponse.getMsgBody().isEmpty()) {
				final var saved = busLocationService.saveResponseList(busLocationApiResponse.getMsgBody());
				log.info(saved.toString());
			} else {
				log.info("busLocationApiResponse.getMsgBody() is null or empty");
			}

		}
	}
}