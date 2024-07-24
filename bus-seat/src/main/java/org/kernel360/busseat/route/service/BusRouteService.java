package org.kernel360.busseat.route.service;

import java.util.List;
import java.util.Optional;

import org.kernel360.busseat.common.dto.Pagination;
import org.kernel360.busseat.common.dto.PaginationDto;
import org.kernel360.busseat.openapi.service.BusRouteListApiService;
import org.kernel360.busseat.route.dto.DataDto;
import org.kernel360.busseat.route.dto.RouteDto;
import org.kernel360.busseat.route.dto.RouteSearchResponseDto;
import org.kernel360.busseat.route.dto.StationsDto;
import org.kernel360.busseat.route.entity.BusRouteEntity;
import org.kernel360.busseat.route.repository.BusRouteRepository;
import org.kernel360.busseat.route_station.entity.BusRouteStationEntity;
import org.kernel360.busseat.route_station.repository.BusRouteStationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusRouteService {
	private final BusRouteRepository busRouteRepository;
	private final BusRouteStationRepository busRouteStationRepository;
	private final BusRouteListApiService busRouteListApiService;

	public Optional<RouteDto> findBusRouteById(Long routeId) {
		return busRouteRepository.findById(String.valueOf(routeId)).map(this::toDto);
	}

	private RouteDto toDto(BusRouteEntity entity) {
		return RouteDto.builder()
			.routeId(entity.getRouteId())
			.routeName(entity.getRouteName())
			.build();
	}

	public DataDto<RouteSearchResponseDto> getRoutesFromApi(String keyword) {
		final var response = busRouteListApiService.request(keyword);
		return DataDto.<RouteSearchResponseDto>builder()
			.data(response.getMsgBody().stream().map(
				it -> RouteSearchResponseDto.builder()
					.routeId(it.getRouteId())
					.routeName(it.getRouteName())
					.regionName(it.getRegionName())
					.build()
			).toList())
			.build();
	}

	public PaginationDto<StationsDto> getStationNamesByRouteName(String routeName, int pageNumber, int pageSize) {
		BusRouteEntity busRoute = busRouteRepository.findByRouteName(routeName);
		if (busRoute == null) {
			throw new RuntimeException("경로가 없습니다");
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<BusRouteStationEntity> busStations = busRouteStationRepository.findAllByRouteId(
			busRoute.getRouteId(), pageable);
		List<StationsDto> stationsDtos = busStations.stream().map(
			it -> StationsDto.builder()
				.name(it.getStationName())
				.build()
		).toList();
		Pagination pagination = Pagination.builder()
			.currentPage((long)pageNumber)
			.totalPage((long)busStations.getTotalPages())
			.build();

		return PaginationDto.<StationsDto>builder()
			.pagination(pagination)
			.data(stationsDtos)
			.build();
	}

}
