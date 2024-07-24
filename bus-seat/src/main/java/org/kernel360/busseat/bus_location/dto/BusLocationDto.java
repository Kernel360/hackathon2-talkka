package org.kernel360.busseat.bus_location.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BusLocationDto {
	private Long busLocationId;
	private Long routeId;
	private Long stationId;
	private Integer stationSeq;
	private String endBus;
	private String lowPlate;
	private String plateNo;
	private String plateType;
	private Integer remainSeatCount;
	private Timestamp createdAt;
}
