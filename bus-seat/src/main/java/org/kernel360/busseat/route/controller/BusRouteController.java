package org.kernel360.busseat.route.controller;

import java.util.Optional;

import org.kernel360.busseat.common.dto.PaginationDto;
import org.kernel360.busseat.route.dto.RouteDto;
import org.kernel360.busseat.route.dto.StationsDto;
import org.kernel360.busseat.route.service.BusRouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class BusRouteController {
	private final BusRouteService busRouteService;

	@GetMapping("/{routeId}")
	public Optional<RouteDto> findByStationId(
		@PathVariable("routeId") Long routeId
	) {
		return busRouteService.findBusRouteById(routeId);
	}

	@PostMapping("/bus-station")
	public PaginationDto<StationsDto> RequestStations(
		@RequestParam String search,
		@RequestParam int page_number,
		@RequestParam int page_size) {
		return busRouteService.getStationNamesByRouteName(search, page_number, page_size);
	}

}

