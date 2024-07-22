package org.kernel360.busseat.route_station.repository;

import java.util.List;
import java.util.Optional;

import org.kernel360.busseat.route_station.entity.BusRouteStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRouteStationRepository extends JpaRepository<BusRouteStationEntity, Long> {
	public List<BusRouteStationEntity> findByRouteId(Long routeId);

	public Optional<BusRouteStationEntity> findByRouteIdAndStationSeq(Long routeId, Short seq);
}
