package org.kernel360.busseat.route_station.service;

import java.util.List;
import java.util.Optional;

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
		return this.busRouteStationRepository.findByRouteId(routeId)
			.stream().map(this::toDto).toList();
	}

	public Optional<RouteStationDto> findByRouteIdAndSeq(Long routeId, Short seq) {
		return this.busRouteStationRepository.findByRouteIdAndStationSeq(routeId, seq).map(this::toDto);
	}

	private RouteStationDto toDto(BusRouteStationEntity entity) {
		return RouteStationDto.builder()
			.busRouteStopId(entity.getBusRouteStopId())
			.routeId(entity.getRouteId())
			.stationId(entity.getStationId())
			.stationSeq(entity.getStationSeq())
			.stationName(entity.getStationName())
			.regionName(entity.getRegionName())
			.districtCd(entity.getDistrictCd())
			.build();
	}
}
