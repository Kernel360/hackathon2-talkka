package org.kernel360.busseat.route_station.controller;

import java.util.List;

import org.kernel360.busseat.route_station.dto.RouteStationDto;
import org.kernel360.busseat.route_station.service.RouteStationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/route-stations")
@RequiredArgsConstructor
public class RouteStationController {
	private final RouteStationService routeStationService;

	@GetMapping("/{routeStationId}")
	public RouteStationDto findById(@PathVariable("routeStationId") Long routeStationId) {
		return routeStationService.findById(routeStationId).orElse(null);
	}

	@GetMapping("")
	public List<RouteStationDto> findAll() {
		return routeStationService.findAll();
	}

	@GetMapping("/route/{routeId}")
	public List<RouteStationDto> findByRouteId(@PathVariable("routeId") Long routeId) {
		return routeStationService.findByRouteId(routeId);
	}

}
