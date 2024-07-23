package org.kernel360.busseat.schedule.repository;

import org.kernel360.busseat.schedule.entity.BusLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusLocationRepository extends JpaRepository<BusLocationEntity, Long> {
}
