package org.kernel360.busseat.route_location.repository;

import org.kernel360.busseat.route_location.entity.BusRouteLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRouteLocationRepository extends JpaRepository<BusRouteLocationEntity, Long> {
}
