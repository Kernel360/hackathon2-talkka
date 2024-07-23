package org.kernel360.busseat.route.service;

import java.util.List;
import java.util.Optional;

import org.kernel360.busseat.route.dto.RouteDto;
import org.kernel360.busseat.route.entity.BusRouteEntity;
import org.kernel360.busseat.route.repository.BusRouteRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusRouteService {
	private final BusRouteRepository busRouteRepository;

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
}
