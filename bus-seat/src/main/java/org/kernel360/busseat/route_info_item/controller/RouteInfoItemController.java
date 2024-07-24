package org.kernel360.busseat.route_info_item.controller;

import org.kernel360.busseat.common.dto.PaginationDto;
import org.kernel360.busseat.route_info_item.dto.RouteInfoItemDto;
import org.kernel360.busseat.route_info_item.service.RouteInfoItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/route-info")
@RequiredArgsConstructor
public class RouteInfoItemController {
	private final RouteInfoItemService routeInfoItemService;

	@GetMapping("")
	public PaginationDto<RouteInfoItemDto> RequestRoute(
		@RequestParam String search,
		@RequestParam int page_number,
		@RequestParam int page_size) {

		return routeInfoItemService.getRouteByName(search, page_number, page_size);

	}

}
