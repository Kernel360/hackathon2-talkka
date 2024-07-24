package org.kernel360.busseat.station.repository;

import java.util.List;

import org.kernel360.busseat.station.entity.BusStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusStationRepository extends JpaRepository<BusStationEntity, Long> {
	List<BusStationEntity> findByStationNameContaining(String name);

	BusStationEntity findByStationName(String name);
}
