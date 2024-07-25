package org.kernel360.busseat.bus_location.repository;

import java.util.List;

import org.kernel360.busseat.bus_location.entity.BusLocationEntity;
import org.kernel360.busseat.route.dto.SeatAvgInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusLocationRepository extends JpaRepository<BusLocationEntity, Long> {
	List<BusLocationEntity> findByRouteId(Long routeId);

	/**
	 * 노선별, 정류장별, 시간대별 평균 남은 좌석수 조회
	 * @param routeId 노선 ID
	 * @param stationId 정류장 ID
	 * @param timeBegin 시작 시간
	 * @param timeEnd 종료 시간
	 * @return 평균 남은 좌석수
	 */
	@Query(value =
		"SELECT DATE_FORMAT(DATE_SUB(b.created_at, INTERVAL MINUTE(b.created_at) % 5 MINUTE), '%H:%i') AS timeInterval, "
			+
			"AVG(b.remain_seat_count) AS avgSeats " +
			"FROM bus_location b " +
			"WHERE b.route_id = :routeId " +
			"AND b.station_id = :stationId " +
			"AND TIME(b.created_at) BETWEEN :timeBegin AND :timeEnd " +
			"GROUP BY timeInterval " +
			"ORDER BY timeInterval", nativeQuery = true)
	List<SeatAvgInterface> findAverageRemainSeats(
		@Param("routeId") Long routeId,
		@Param("stationId") Long stationId,
		@Param("timeBegin") String timeBegin,
		@Param("timeEnd") String timeEnd);

}
