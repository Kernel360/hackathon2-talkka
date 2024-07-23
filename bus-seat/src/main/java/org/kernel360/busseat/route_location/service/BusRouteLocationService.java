package org.kernel360.busseat.route_location.service;

import org.kernel360.busseat.route_location.repository.BusRouteLocationRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusRouteLocationService {
	private final BusRouteLocationRepository busLocationRepository;

}