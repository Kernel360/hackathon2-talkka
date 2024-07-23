package org.kernel360.busseat.route_station.dto;

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

	private Short stationSeq;

	private String stationName;

	private String regionName;

	private String districtCd;
}
