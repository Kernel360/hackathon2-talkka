package org.kernel360.busseat.schedule.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bus_route_location")
public class BusLocationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long busRouteLocationId;

	private Long routeId;

	private Long stationId;

	private Short stationSeq;

	private Character endBus;

	private Character lowPlate;

	private String plateNo;

	private Character plateType;

	private Short remainSeatCount;

	private Timestamp createdAt;
}

