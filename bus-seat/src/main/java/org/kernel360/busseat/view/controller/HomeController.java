package org.kernel360.busseat.view.controller;

import org.kernel360.busseat.route.dto.RouteDto;
import org.kernel360.busseat.route.service.BusRouteService;
import org.kernel360.busseat.schedule.service.BusLocationService;
import org.kernel360.busseat.station.dto.StationDto;
import org.kernel360.busseat.station.service.BusStationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final BusRouteService busRouteService;
	private final BusStationService busStationService;
	private final BusLocationService busLocationService;

	@GetMapping("/busInfo")
	public String showBusInfoForm() {
		return "home/busInfo";
	}

	@PostMapping("/busInfo")
	public String getBusInfo(@RequestParam("routeName") String routeName,
		@RequestParam("stationName") String stationName,
		Model model) {
		StationDto station = busStationService.searchByStationName(stationName);
		RouteDto route = busRouteService.findBusSeatInfo(routeName);

		if (station == null || route == null) {
			model.addAttribute("error", "No matching station or route found.");
			return "home/busInfo";
		}

		Long stationId = station.getStationId();
		Long routeId = route.getRouteId(); // 첫 번째 일치하는 노선 사용

		var busRouteLocations = busLocationService.getBusSeatInfoByRouteAndStation(routeId, stationId);
		model.addAttribute("busRouteLocations", busRouteLocations);
		model.addAttribute("routeName", routeName);
		model.addAttribute("stationName", stationName);

		return "home/busInfo";
	}

}