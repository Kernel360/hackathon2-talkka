package org.kernel360.busseat.route_station.repository;

import java.util.List;

import org.kernel360.busseat.route_station.entity.BusRouteStationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRouteStationRepository extends JpaRepository<BusRouteStationEntity, Long> {
	Page<BusRouteStationEntity> findAllByRouteId(Long routeId, Pageable pa);

	List<BusRouteStationEntity> findAllByRouteId(Long id);

}
