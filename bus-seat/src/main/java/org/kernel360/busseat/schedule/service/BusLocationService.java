package org.kernel360.busseat.schedule.service;

import java.util.List;

import org.kernel360.busseat.schedule.entity.BusRouteLocationEntity;
import org.kernel360.busseat.schedule.repository.BusRouteLocationRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusLocationService {
	private final BusRouteLocationRepository busLocationRepository;

	public List<BusRouteLocationEntity> getBusSeatInfoByRouteAndStation(Long routeId,
		Long stationId) {
		System.out.println("Getting bus seat info for routeId: " + routeId + ", stationId: " + stationId);
		List<BusRouteLocationEntity> busRouteLocations = busLocationRepository.findByRouteIdAndStationIdOrderByCreatedAtAsc(
			routeId, stationId);
		System.out.println("BusRouteLocations found: " + busRouteLocations.size());
		return busRouteLocations;
	}

}