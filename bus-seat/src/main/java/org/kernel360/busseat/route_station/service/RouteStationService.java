package org.kernel360.busseat.route_station.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.kernel360.busseat.openapi.dto.BusRouteStationListBody;
import org.kernel360.busseat.route_station.dto.RouteStationDto;
import org.kernel360.busseat.route_station.entity.RouteStationEntity;
import org.kernel360.busseat.route_station.repository.RouteStationRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteStationService {
	private final RouteStationRepository routeStationRepository;

	public Optional<RouteStationDto> findById(Long id) {
		return this.routeStationRepository.findById(id).map(this::toDto);
	}

	public List<RouteStationDto> findAll() {
		return this.routeStationRepository.findAll()
			.stream().map(this::toDto).toList();
	}

	public List<RouteStationDto> findByRouteId(Long routeId) {
		return this.routeStationRepository.findAllByRouteId(routeId)
			.stream().map(this::toDto).toList();
	}

	public RouteStationDto toDto(RouteStationEntity entity) {
		return RouteStationDto.builder()
			.busRouteStationId(entity.getBusRouteStationId())
			.routeId(entity.getRouteId())
			.stationId(entity.getStationId())
			.stationSeq(entity.getStationSeq())
			.stationName(entity.getStationName())
			.regionName(entity.getRegionName())
			.districtCd(entity.getDistrictCd())
			.centerYn(entity.getCenterYn())
			.turnYn(entity.getTurnYn())
			.longitude(entity.getLongitude())
			.latitude(entity.getLatitude())
			.build();
	}

	public RouteStationEntity toEntity(BusRouteStationListBody dto, Long routeId) {
		return RouteStationEntity.builder()
			.routeId(routeId)
			.stationId(dto.getStationId())
			.stationSeq(dto.getStationSeq())
			.stationName(dto.getStationName())
			.regionName(dto.getRegionName())
			.districtCd(dto.getDistrictCd())
			.centerYn(dto.getCenterYn())
			.turnYn(dto.getTurnYn())
			.longitude(BigDecimal.valueOf(dto.getX()))
			.latitude(BigDecimal.valueOf(dto.getY()))
			.createdAt(new Timestamp(System.currentTimeMillis()))
			.build();
	}

	public void saveAll(List<RouteStationEntity> entities) {
		this.routeStationRepository.saveAll(entities);
	}
}
