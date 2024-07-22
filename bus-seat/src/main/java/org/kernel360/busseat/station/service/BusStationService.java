package org.kernel360.busseat.station.service;

import java.util.Optional;

import org.kernel360.busseat.station.entity.BusStationEntity;
import org.kernel360.busseat.station.repository.BusStationRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusStationService {
	private final BusStationRepository busStationRepository;

	//조회
	public Optional<BusStationEntity> findBusStationById(Long stationId) {
		return busStationRepository.findById(stationId);
	}
}
