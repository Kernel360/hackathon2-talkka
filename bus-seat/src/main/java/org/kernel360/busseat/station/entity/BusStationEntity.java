package org.kernel360.busseat.station.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

}