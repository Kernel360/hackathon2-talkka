package org.kernel360.busseat.route.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteDto {
	private Long routeId;
	private String routeName;
	private String stationStart;
	private String stationEnd;
	private String regionName;
}
