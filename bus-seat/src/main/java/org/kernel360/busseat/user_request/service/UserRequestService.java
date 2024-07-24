package org.kernel360.busseat.user_request.service;

import java.sql.Timestamp;
import java.util.List;

import org.kernel360.busseat.openapi.dto.BusRouteStationListBody;
import org.kernel360.busseat.openapi.dto.BusRouteStationListResponse;
import org.kernel360.busseat.openapi.dto.RouteInfoItemApiResponse;
import org.kernel360.busseat.openapi.service.BusRouteStationApiService;
import org.kernel360.busseat.openapi.service.RouteInfoItemApiService;
import org.kernel360.busseat.route.repository.BusRouteRepository;
import org.kernel360.busseat.route_info_item.entity.RouteInfoItemEntity;
import org.kernel360.busseat.route_info_item.repository.RouteInfoItemRepository;
import org.kernel360.busseat.route_info_item.service.RouteInfoItemService;
import org.kernel360.busseat.route_station.service.BusRouteStationService;
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

	private final RouteInfoItemRepository routeInfoItemRepository;
	private final RouteInfoItemApiService routeInfoItemApiService;
	private final RouteInfoItemService routeInfoItemService;

	private final BusRouteRepository busRouteRepository;

	private final BusRouteStationApiService busRouteStationApiService;
	private final BusRouteStationService busRouteStationService;

	@Transactional
	public void create(UserRequestDto userRequestDto) {
		// check if routeId is valid
		final var exist = routeInfoItemRepository.findByRouteName(userRequestDto.getRouteName());
		if (exist != null) {
			return;
		}

		// get route info
		final Long routeId = busRouteRepository.findByRouteName(userRequestDto.getRouteName()).getRouteId();
		final RouteInfoItemApiResponse infoResponse = routeInfoItemApiService.request(routeId.toString());
		final var routeInfoItem = infoResponse.getMsgBody().get(0);
		final RouteInfoItemEntity routeInfoItemEntity = routeInfoItemService.toEntity(routeInfoItem);
		final var saved = routeInfoItemRepository.save(routeInfoItemEntity);

		// get route station info
		final BusRouteStationListResponse stationResponse = busRouteStationApiService.request(routeId.toString());
		final List<BusRouteStationListBody> stationList = stationResponse.getMsgBody();
		final var stationListEntity = stationList.stream()
			.map((it) -> busRouteStationService.toEntity(it, saved.getRouteId()))
			.toList();
		busRouteStationService.saveAll(stationListEntity);

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
