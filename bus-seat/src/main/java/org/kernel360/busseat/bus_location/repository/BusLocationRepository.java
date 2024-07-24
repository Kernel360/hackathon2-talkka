package org.kernel360.busseat.bus_location.repository;

import java.util.List;

import org.kernel360.busseat.bus_location.entity.BusLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusLocationRepository extends JpaRepository<BusLocationEntity, Long> {
	List<BusLocationEntity> findByRouteId(Long routeId);
}
