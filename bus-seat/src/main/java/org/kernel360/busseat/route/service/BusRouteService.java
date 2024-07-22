package org.kernel360.busseat.route.service;

import java.util.Optional;

import org.kernel360.busseat.route.entity.BusRouteEntity;
import org.kernel360.busseat.route.repository.BusRouteRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusRouteService {
	private final BusRouteRepository busRouteRepository;

	//1.조회
	public Optional<BusRouteEntity> findBusRouteById(Long routeId) {
		return busRouteRepository.findById(String.valueOf(routeId));
	}
}
