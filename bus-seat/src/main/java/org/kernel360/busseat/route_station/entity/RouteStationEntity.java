package org.kernel360.busseat.route_station.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.kernel360.busseat.route.entity.RouteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "bus_route_station")
public class RouteStationEntity {
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

	@Column(name = "center_yn", nullable = false)
	private String centerYn;

	@Column(name = "turn_yn", nullable = false)
	private String turnYn;

	@Column(name = "longitude", nullable = false)
	private BigDecimal longitude;

	@Column(name = "latitude", nullable = false)
	private BigDecimal latitude;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	// 연관 관계
	@ManyToOne
	@JoinColumn(name = "route_id", insertable = false, updatable = false)
	private RouteEntity routeEntity;
}
