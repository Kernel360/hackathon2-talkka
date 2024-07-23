package org.kernel360.busseat.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusLocation {
	@JsonProperty("endBus")
	private Character endBus;

	@JsonProperty("lowPlate")
	private Character lowPlate;

	@JsonProperty("plateNo")
	private String plateNo;

	@JsonProperty("plateType")
	private Character plateType;

	@JsonProperty("remainSeatCnt")
	private Short remainSeatCount;

	@JsonProperty("routeId")
	private Long routeId;

	@JsonProperty("stationId")
	private Long stationId;

	@JsonProperty("stationSeq")
	private Short stationSeq;

}
