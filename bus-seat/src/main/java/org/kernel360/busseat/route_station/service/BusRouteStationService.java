package org.kernel360.busseat.route_station.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.kernel360.busseat.openapi.dto.BusRouteStationListBody;
import org.kernel360.busseat.route_station.dto.RouteStationDto;
import org.kernel360.busseat.route_station.entity.BusRouteStationEntity;
import org.kernel360.busseat.route_station.repository.BusRouteStationRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusRouteStationService {
	private final BusRouteStationRepository busRouteStationRepository;

	public Optional<RouteStationDto> findById(Long id) {
		return this.busRouteStationRepository.findById(id).map(this::toDto);
	}

	public List<RouteStationDto> findAll() {
		return this.busRouteStationRepository.findAll()
			.stream().map(this::toDto).toList();
	}

	public List<RouteStationDto> findByRouteId(Long routeId) {
		return this.busRouteStationRepository.findAllByRouteId(routeId)
			.stream().map(this::toDto).toList();
	}

	public RouteStationDto toDto(BusRouteStationEntity entity) {
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
			.build();
	}

	public BusRouteStationEntity toEntity(BusRouteStationListBody dto, Long routeId) {
		return BusRouteStationEntity.builder()
			.routeId(routeId)
			.stationId(dto.getStationId())
			.stationSeq(dto.getStationSeq())
			.stationName(dto.getStationName())
			.regionName(dto.getRegionName())
			.districtCd(dto.getDistrictCd())
			.centerYn(dto.getCenterYn())
			.turnYn(dto.getTurnYn())
			.createdAt(new Timestamp(System.currentTimeMillis()))
			.build();
	}
}
