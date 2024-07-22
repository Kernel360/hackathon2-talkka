package org.kernel360.busseat.user_request.controller;

import org.kernel360.busseat.user_request.dto.UserRequestDto;
import org.kernel360.busseat.user_request.service.UserRequestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/collect")
@RequiredArgsConstructor
public class UserRequestController {
	private final UserRequestService userRequestService;

	@PostMapping()
	public UserRequestDto create(@PathVariable("routeId") Long routeId, @PathVariable("routeName") String routeName) {
		return userRequestService.create(routeId, routeName);
	}
}
