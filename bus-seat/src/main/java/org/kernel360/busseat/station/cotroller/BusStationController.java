package org.kernel360.busseat.station.cotroller;

import java.util.Optional;

import org.kernel360.busseat.station.entity.BusStationEntity;
import org.kernel360.busseat.station.service.BusStationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/station")
@RequiredArgsConstructor
public class BusStationController {
	private final BusStationService busStationService;

	@GetMapping("")
	public Optional<BusStationEntity> findByStationId(
		@RequestParam Long stationId
	) {
		return busStationService.findBusStationById(stationId);
	}
}
