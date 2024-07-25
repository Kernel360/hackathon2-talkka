package org.kernel360.busseat.route.service;

import java.util.List;
import java.util.Optional;

import org.kernel360.busseat.bus_location.repository.BusLocationRepository;
import org.kernel360.busseat.common.dto.Pagination;
import org.kernel360.busseat.common.dto.PaginationDto;
import org.kernel360.busseat.openapi.dto.BusRouteInfoItemApiBody;
import org.kernel360.busseat.openapi.service.BusRouteListApiService;
import org.kernel360.busseat.route.dto.DataDto;
import org.kernel360.busseat.route.dto.RouteDto;
import org.kernel360.busseat.route.dto.RouteSearchResponseDto;
import org.kernel360.busseat.route.dto.SeatAvg;
import org.kernel360.busseat.route.dto.StationsDto;
import org.kernel360.busseat.route.entity.RouteEntity;
import org.kernel360.busseat.route.repository.RouteRepository;
import org.kernel360.busseat.route_station.entity.RouteStationEntity;
import org.kernel360.busseat.route_station.repository.RouteStationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteService {
	private final RouteRepository routeRepository;
	private final RouteStationRepository routeStationRepository;
	private final BusRouteListApiService busRouteListApiService;
	private final BusLocationRepository busLocationRepository;

	public Optional<RouteDto> findBusRouteById(Long routeId) {
		return routeRepository.findById(routeId).map(this::toDto);
	}

	public List<SeatAvg> getSeats(Long routeId, Long stationId, String timeBegin, String timeEnd) {
		// DB 에서 시간에 따라 집계해서 가져와야합니다.
		return busLocationRepository.findAverageRemainSeats(routeId, stationId,
			timeBegin, timeEnd).stream().map(
			it -> SeatAvg.builder()
				.timeInterval(it.getTimeInterval())
				.avgSeats(it.getAvgSeats())
				.build()
		).toList();
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

	public PaginationDto<StationsDto> getStationsById(Long routeId, int pageNumber, int pageSize) {
		final RouteEntity routeEntity = routeRepository.findById(routeId).orElse(null);
		if (routeEntity == null) {
			throw new RuntimeException("경로가 없습니다");
		}

		final Pageable pageable = PageRequest.of(pageNumber, pageSize);
		final Page<RouteStationEntity> busStationEntityPage = routeStationRepository.findAllByRouteId(
			routeEntity.getRouteId(), pageable);
		final List<StationsDto> stationsDtoList = busStationEntityPage.stream().map(
			it -> StationsDto.builder()
				.stationId(it.getStationId())
				.stationName(it.getStationName())
				.stationSeq(it.getStationSeq())
				.turnYn(it.getTurnYn())
				.centerYn(it.getCenterYn())
				.longitude(it.getLongitude())
				.latitude(it.getLatitude())
				.build()
		).toList();
		final Pagination pagination = Pagination.builder()
			.currentPage((long)pageNumber)
			.totalPage((long)busStationEntityPage.getTotalPages())
			.build();

		return PaginationDto.<StationsDto>builder()
			.pagination(pagination)
			.data(stationsDtoList)
			.build();
	}

	public PaginationDto<RouteDto> getRouteByName(String routeName, int pageNumber, int pageSize) {
		final Pageable pageable = PageRequest.of(pageNumber, pageSize);
		final Page<RouteEntity> routePage = routeRepository.findByRouteNameContaining(routeName, pageable);
		final List<RouteDto> routeDtoList = routePage.stream()
			.map(entity -> RouteDto.builder()
				.routeId(entity.getRouteId())
				.routeName(entity.getRouteName())
				.stationStart(entity.getStartStationName())
				.stationEnd(entity.getEndStationName())
				.regionName(entity.getRegionName())
				.build())
			.toList();
		final Pagination pagination = Pagination.builder()
			.currentPage((long)pageNumber)
			.totalPage((long)routePage.getTotalPages())
			.build();

		return PaginationDto.<RouteDto>builder()
			.pagination(pagination)
			.data(routeDtoList)
			.build();

	}

	public RouteDto toDto(RouteEntity entity) {
		return RouteDto.builder()
			.routeId(entity.getRouteId())
			.routeName(entity.getRouteName())
			.build();
	}

	public RouteEntity toEntity(BusRouteInfoItemApiBody busRouteInfoItemApiBody) {
		return RouteEntity.builder()
			.routeId(busRouteInfoItemApiBody.getRouteId())
			.routeName(busRouteInfoItemApiBody.getRouteName())
			.regionName(busRouteInfoItemApiBody.getRegionName())
			.startStationName(busRouteInfoItemApiBody.getStartStationName())
			.endStationName(busRouteInfoItemApiBody.getEndStationName())
			.endStationId(busRouteInfoItemApiBody.getEndStationId())
			.startStationId(busRouteInfoItemApiBody.getStartStationId())
			.startMobileNo(busRouteInfoItemApiBody.getStartMobileNo())
			.companyId(busRouteInfoItemApiBody.getCompanyId())
			.companyName(busRouteInfoItemApiBody.getCompanyName())
			.companyTel(busRouteInfoItemApiBody.getCompanyTel())
			.districtCd(busRouteInfoItemApiBody.getDistrictCd())
			.downFirstTime(busRouteInfoItemApiBody.getDownFirstTime())
			.downLastTime(busRouteInfoItemApiBody.getDownLastTime())
			.upFirstTime(busRouteInfoItemApiBody.getUpFirstTime())
			.upLastTime(busRouteInfoItemApiBody.getUpLastTime())
			.routeTypeCd(busRouteInfoItemApiBody.getRouteTypeCd())
			.routeTypeName(busRouteInfoItemApiBody.getRouteTypeName())
			.build();
	}
}
