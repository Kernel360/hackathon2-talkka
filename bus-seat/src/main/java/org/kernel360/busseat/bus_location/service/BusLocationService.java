package org.kernel360.busseat.bus_location.service;

import java.sql.Timestamp;
import java.util.List;

import org.kernel360.busseat.bus_location.dto.BusLocationDto;
import org.kernel360.busseat.bus_location.entity.BusLocationEntity;
import org.kernel360.busseat.bus_location.repository.BusLocationRepository;
import org.kernel360.busseat.openapi.dto.BusLocationApiBody;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusLocationService {
	private final BusLocationRepository busLocationRepository;

	public BusLocationDto save(BusLocationApiBody busLocationApiBody) {
		final BusLocationEntity busLocationEntity = toEntity(busLocationApiBody);
		final BusLocationEntity savedBusLocationEntity = busLocationRepository.save(busLocationEntity);
		return toDto(savedBusLocationEntity);
	}

	public List<BusLocationDto> saveResponseList(List<BusLocationApiBody> busLocationApiBodies) {
		final List<BusLocationEntity> busLocationEntities = busLocationApiBodies.stream()
			.map(this::toEntity)
			.toList();
		final List<BusLocationEntity> savedBusLocationEntities = busLocationRepository.saveAll(busLocationEntities);
		return savedBusLocationEntities.stream()
			.map(this::toDto)
			.toList();
	}

	private BusLocationDto toDto(BusLocationEntity busLocationEntity) {
		return BusLocationDto.builder()
			.endBus(busLocationEntity.getEndBus())
			.lowPlate(busLocationEntity.getLowPlate())
			.plateNo(busLocationEntity.getPlateNo())
			.plateType(busLocationEntity.getPlateType())
			.remainSeatCount(busLocationEntity.getRemainSeatCount())
			.routeId(busLocationEntity.getRouteId())
			.stationId(busLocationEntity.getStationId())
			.stationSeq(busLocationEntity.getStationSeq())
			.build();
	}

	private BusLocationEntity toEntity(BusLocationApiBody busLocationApiBody) {
		return BusLocationEntity.builder()
			.endBus(busLocationApiBody.getEndBus())
			.lowPlate(busLocationApiBody.getLowPlate())
			.plateNo(busLocationApiBody.getPlateNo())
			.plateType(busLocationApiBody.getPlateType())
			.remainSeatCount(busLocationApiBody.getRemainSeatCnt())
			.routeId(busLocationApiBody.getRouteId())
			.stationId(busLocationApiBody.getStationId())
			.stationSeq(busLocationApiBody.getStationSeq())
			.createdAt(new Timestamp(System.currentTimeMillis()))
			.build();

	}
}