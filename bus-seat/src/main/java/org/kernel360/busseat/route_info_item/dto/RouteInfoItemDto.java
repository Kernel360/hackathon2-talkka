package org.kernel360.busseat.route_info_item.dto;

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
public class RouteInfoItemDto {
	private Long routeId;
	private String routeName;
	private String stationStart;
	private String stationEnd;
}
