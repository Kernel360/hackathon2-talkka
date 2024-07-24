package org.kernel360.busseat.route.service;

import java.util.List;
import java.util.Optional;

import org.kernel360.busseat.common.dto.Pagination;
import org.kernel360.busseat.common.dto.PaginationDto;
import org.kernel360.busseat.route.dto.RouteDto;
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

	public Optional<RouteDto> findBusRouteById(Long routeId) {
		return busRouteRepository.findById(String.valueOf(routeId)).map(this::toDto);
	}

	public List<RouteDto> findAll() {
		return busRouteRepository.findAll().stream()
			.map(this::toDto)
			.toList();
	}

	public List<RouteDto> searchByRouteNames(String name) {
		return busRouteRepository.findByRouteNameContaining(name).stream()
			.map(this::toDto)
			.toList();
	}

	public RouteDto searchByRouteName(String name) {
		return toDto(busRouteRepository.findByRouteName(name));
	}

	private RouteDto toDto(BusRouteEntity entity) {
		return RouteDto.builder()
			.routeId(entity.getRouteId())
			.routeName(entity.getRouteName())
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
