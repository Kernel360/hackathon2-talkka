package org.kernel360.busseat.schedule.repository;

import java.util.List;

import org.kernel360.busseat.schedule.entity.BusRouteLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusRouteLocationRepository extends JpaRepository<BusRouteLocationEntity, Long> {
	@Query("SELECT b FROM BusRouteLocationEntity b WHERE b.routeId = :routeId AND b.stationId = :stationId ORDER BY b.createdAt DESC")
	List<BusRouteLocationEntity> findByRouteIdAndStationIdOrderByCreatedAtAsc(@Param("routeId") Long routeId,
		@Param("stationId") Long stationId); // @Param 어노테이션 추가
}
