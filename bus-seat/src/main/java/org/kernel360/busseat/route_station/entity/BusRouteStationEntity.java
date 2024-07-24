package org.kernel360.busseat.route_station.entity;

import java.sql.Timestamp;

import org.kernel360.busseat.route.entity.BusRouteEntity;
import org.kernel360.busseat.station.entity.BusStationEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private Long busRouteStationId;

	@Column(name = "route_id", nullable = false)
	private Long routeId;

	@Column(name = "station_id", nullable = false)
	private Long stationId;

	@Column(name = "station_seq", nullable = false)
	private Integer stationSeq;

	@Column(name = "station_name", nullable = false)
	private String stationName;

	@Column(name = "region_name", nullable = false)
	private String regionName;

	@Column(name = "district_cd", nullable = false)
	private String districtCd;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	// 연관 관계
	@ManyToOne
	@JoinColumn(name = "route_id", insertable = false, updatable = false)
	private BusRouteEntity busRouteEntity;

	@ManyToOne
	@JoinColumn(name = "station_id", insertable = false, updatable = false)
	private BusStationEntity busStationEntity;
}
