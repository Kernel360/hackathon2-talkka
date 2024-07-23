package org.kernel360.busseat.schedule.entity;

import java.sql.Timestamp;

import org.kernel360.busseat.station.entity.BusStationEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bus_route_location")
public class BusRouteLocationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bus_route_location_id", nullable = false)
	private Long busRouteLocationId;

	@Column(name = "route_id", nullable = false)
	private Long routeId;

	@Column(name = "station_id", nullable = false)
	private Long stationId;

	@Column(name = "station_seq", nullable = false)
	private Short stationSeq;

	@Column(name = "end_bus", nullable = false)
	private Character endBus;

	@Column(name = "low_plate", nullable = false)
	private Character lowPlate;

	@Column(name = "plate_no", nullable = false)
	private String plateNo;

	@Column(name = "plate_type", nullable = false)
	private Character plateType;

	@Column(name = "remain_seat_count", nullable = false)
	private Short remainSeatCount;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@ManyToOne
	@JoinColumn(name = "station_id", insertable = false, updatable = false)
	private BusStationEntity busStationEntity;
}
