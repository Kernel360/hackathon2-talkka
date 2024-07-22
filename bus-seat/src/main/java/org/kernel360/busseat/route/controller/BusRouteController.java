package org.kernel360.busseat.route.controller;

import java.util.List;
import java.util.Optional;

import org.kernel360.busseat.route.dto.RouteDto;
import org.kernel360.busseat.route.service.BusRouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class BusRouteController {
	private final BusRouteService busRouteService;

	@GetMapping("/{routeId}")
	public Optional<RouteDto> findByStationId(
		@PathVariable("routeId") Long routeId
	) {
		return busRouteService.findBusRouteById(routeId);
	}

	@GetMapping("/")
	public List<RouteDto> findAll(
		@RequestParam("routeName") String routeName
	) {
		if (routeName == null) {
			return busRouteService.findAll();
		}
		return busRouteService.searchByRouteName(routeName);
	}
}
