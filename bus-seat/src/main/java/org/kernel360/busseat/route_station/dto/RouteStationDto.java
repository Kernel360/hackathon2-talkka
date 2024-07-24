package org.kernel360.busseat.route_station.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteStationDto {
	private Long busRouteStationId;

	private Long routeId;

	private Long stationId;

	private Integer stationSeq;

	private String stationName;

	private String regionName;

	private String districtCd;

	private String centerYn;

	private String turnYn;

	private BigDecimal longitude;

	private BigDecimal latitude;
}
