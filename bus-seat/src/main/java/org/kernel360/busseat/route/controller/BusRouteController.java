package org.kernel360.busseat.route.controller;

import java.util.Optional;

import org.kernel360.busseat.route.entity.BusRouteEntity;
import org.kernel360.busseat.route.service.BusRouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class BusRouteController {
	private final BusRouteService busRouteService;

	@GetMapping("")
	public Optional<BusRouteEntity> findByRoutesId(
		@RequestParam Long routeId
	) {
		return busRouteService.findBusRouteById(routeId);
	}
}
