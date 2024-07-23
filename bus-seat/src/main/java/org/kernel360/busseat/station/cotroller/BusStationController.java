package org.kernel360.busseat.station.cotroller;

import java.util.List;

import org.kernel360.busseat.station.dto.StationDto;
import org.kernel360.busseat.station.service.BusStationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/station")
@RequiredArgsConstructor
public class BusStationController {
	private final BusStationService busStationService;

	@GetMapping("/{stationId}")
	public StationDto findByStationId(
		@PathVariable("stationId") Long stationId
	) {
		return busStationService.findBusStationById(stationId).orElseThrow(); // 처리 필요함.
	}

	@GetMapping("/")
	public List<StationDto> findAll(
		@RequestParam("stationName") String stationName
	) {
		if (stationName == null) {
			return busStationService.findAll();
		}
		return busStationService.searchByStationNames(stationName);
	}
}
