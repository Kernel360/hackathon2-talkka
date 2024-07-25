package org.kernel360.busseat.route_station.repository;

import java.util.List;

import org.kernel360.busseat.route_station.entity.RouteStationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteStationRepository extends JpaRepository<RouteStationEntity, Long> {
	Page<RouteStationEntity> findAllByRouteId(Long routeId, Pageable pa);

	List<RouteStationEntity> findAllByRouteId(Long id);

}
