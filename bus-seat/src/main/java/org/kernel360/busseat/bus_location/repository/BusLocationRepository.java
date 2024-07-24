package org.kernel360.busseat.bus_location.repository;

import org.kernel360.busseat.bus_location.entity.BusLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusLocationRepository extends JpaRepository<BusLocationEntity, Long> {
}
