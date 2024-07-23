package org.kernel360.busseat.station.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import org.kernel360.busseat.schedule.entity.BusRouteLocationEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bus_station")
public class BusStationEntity {
	@Id
	private Long stationId;

	private String stationName;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String stationType;

	private Timestamp registeredAt;

	private Timestamp createdAt;

	@OneToMany(mappedBy = "busStationEntity")
	private Set<BusRouteLocationEntity> busLocations;
}
