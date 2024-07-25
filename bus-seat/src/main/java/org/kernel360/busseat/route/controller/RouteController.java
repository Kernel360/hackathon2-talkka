package org.kernel360.busseat.route.controller;

import java.util.Optional;

import org.kernel360.busseat.common.dto.PaginationDto;
import org.kernel360.busseat.route.dto.DataDto;
import org.kernel360.busseat.route.dto.RouteDto;
import org.kernel360.busseat.route.dto.RouteSearchResponseDto;
import org.kernel360.busseat.route.dto.SeatAvg;
import org.kernel360.busseat.route.dto.StationsDto;
import org.kernel360.busseat.route.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController {
	private final RouteService routeService;

	@GetMapping("/{routeId}/stations")
	public PaginationDto<StationsDto> getRouteStations(
		@PathVariable("routeId") Long routeId,
		@RequestParam(name = "page", required = false) Optional<Integer> pageNumber,
		@RequestParam(name = "size", required = false) Optional<Integer> pageSize) {
		int page = pageNumber.filter(it -> it > 0).orElse(0);
		int size = pageSize.orElse(10);
		return routeService.getStationsById(routeId, page, size);
	}

	@GetMapping("/search/{keyword}")
	public DataDto<RouteSearchResponseDto> searchByRouteName(
		@PathVariable("keyword") String keyword
	) {
		return routeService.getRoutesFromApi(keyword);
	}

	@GetMapping("/registered")
	public PaginationDto<RouteDto> getRegisteredRoutes(
		@RequestParam String search,
		@RequestParam(name = "page", required = false) Optional<Integer> pageNumber,
		@RequestParam(name = "size", required = false) Optional<Integer> pageSize) {
		int page = pageNumber.filter(it -> it > 0).orElse(0);
		int size = pageSize.orElse(10);
		return routeService.getRouteByName(search, page, size);
	}

	@GetMapping("/{routeId}/seats")
	public DataDto<SeatAvg> getSeats(
		@PathVariable("routeId") Long routeId,
		@RequestParam(name = "stationId", required = true) Long stationId,
		@RequestParam(name = "timeBegin", required = true) String timeBegin,
		@RequestParam(name = "timeEnd", required = true) String timeEnd
	) {
		return DataDto.<SeatAvg>builder()
			.data(routeService.getSeats(routeId, stationId, timeBegin, timeEnd))
			.build();
	}
}

