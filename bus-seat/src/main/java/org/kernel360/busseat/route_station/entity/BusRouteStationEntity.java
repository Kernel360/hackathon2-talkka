package org.kernel360.busseat.route_station.entity;

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
@Entity(name = "bus_route_station")
public class BusRouteStationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long busRouteStopId;

	private Long routeId;

	private Long stationId;

	private Short stationSeq;

	private String stationName;

	private String regionName;

	private String districtCd;

	private Timestamp createdAt;
}
