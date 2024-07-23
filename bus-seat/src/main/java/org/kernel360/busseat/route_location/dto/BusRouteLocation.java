package org.kernel360.busseat.route_location.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusRouteLocation {
	private String endBus;
	private String lowPlate;
	private String plateNo;
	private String plateType;
	private Short remainSeatCount;
	private Long routeId;
	private Long stationId;
	private Short stationSeq;
}
