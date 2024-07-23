package org.kernel360.busseat.station.service;

import java.util.List;
import java.util.Optional;

import org.kernel360.busseat.station.dto.StationDto;
import org.kernel360.busseat.station.entity.BusStationEntity;
import org.kernel360.busseat.station.repository.BusStationRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusStationService {
	private final BusStationRepository busStationRepository;

	public List<StationDto> findAll() {
		return busStationRepository.findAll().stream()
			.map(this::toDto).toList();
	}

	public Optional<StationDto> findBusStationById(Long stationId) {
		final var entity = busStationRepository.findById(stationId);

		return entity.map(this::toDto);
	}

	public List<StationDto> searchByStationNames(String name) {
		return busStationRepository.findByStationNameContaining(name).stream()
			.map(this::toDto)
			.toList();
	}

	public StationDto searchByStationName(String name) {
		BusStationEntity busStationEntity = busStationRepository.findByStationName(name);
		return toDto(busStationEntity);
	}

	private StationDto toDto(BusStationEntity entity) {
		return StationDto.builder()
			.stationId(entity.getStationId())
			.stationName(entity.getStationName())
			.stationType(entity.getStationType())
			.longitude(entity.getLongitude())
			.latitude(entity.getLatitude())
			.build();
	}
}
