package org.kernel360.busseat.user_request.service;

import java.sql.Timestamp;
import java.util.List;

import org.kernel360.busseat.openapi.dto.BusRouteInfoItemApiResponse;
import org.kernel360.busseat.openapi.dto.BusRouteStationListBody;
import org.kernel360.busseat.openapi.dto.BusRouteStationListResponse;
import org.kernel360.busseat.openapi.service.BusRouteInfoItemApiService;
import org.kernel360.busseat.openapi.service.BusRouteStationApiService;
import org.kernel360.busseat.route.entity.RouteEntity;
import org.kernel360.busseat.route.repository.RouteRepository;
import org.kernel360.busseat.route.service.RouteService;
import org.kernel360.busseat.route_station.service.RouteStationService;
import org.kernel360.busseat.user_request.dto.UserRequest;
import org.kernel360.busseat.user_request.dto.UserRequestDto;
import org.kernel360.busseat.user_request.entity.UserRequestEntity;
import org.kernel360.busseat.user_request.repository.UserRequestRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRequestService {

	private final UserRequestRepository userRequestRepository;

	private final BusRouteInfoItemApiService busRouteInfoItemApiService;
	private final RouteService routeService;

	private final RouteRepository routeRepository;

	private final BusRouteStationApiService busRouteStationApiService;
	private final RouteStationService routeStationService;

	@Transactional
	public void create(UserRequestDto userRequestDto) {
		// check if routeId is valid
		final var routeId = userRequestDto.getRouteId();
		final var exist = routeRepository.findById(routeId);
		if (exist.isPresent()) {
			return;
		}

		// get route info
		final BusRouteInfoItemApiResponse infoResponse = busRouteInfoItemApiService.request(routeId.toString());
		final var routeInfoItem = infoResponse.getMsgBody().get(0);
		final RouteEntity routeInfoItemEntity = routeService.toEntity(routeInfoItem);
		final var saved = routeRepository.save(routeInfoItemEntity);

		// get route station info
		final BusRouteStationListResponse stationResponse = busRouteStationApiService.request(routeId.toString());
		final List<BusRouteStationListBody> stationList = stationResponse.getMsgBody();
		final var stationListEntity = stationList.stream()
			.map((it) -> routeStationService.toEntity(it, saved.getRouteId()))
			.toList();
		routeStationService.saveAll(stationListEntity);

		//	save user request
		final UserRequestEntity userRequestEntity = UserRequestEntity.builder()
			.routeId(routeId)
			.status("OK")
			.createdAt(new Timestamp(System.currentTimeMillis()))
			.build();
		userRequestRepository.save(userRequestEntity);
	}

	public List<UserRequest> findAll() {
		return userRequestRepository.findAll().stream()
			.map(this::toDto)
			.toList();
	}

	private UserRequest toDto(UserRequestEntity userRequestEntity) {
		return UserRequest.builder()
			.routeId(userRequestEntity.getRouteId())
			.build();
	}
}
