package org.kernel360.busseat.schedule.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
public class BusLocationDto {
	@JsonProperty("comMsgHeader")
	private Object comMsgHeader;

	@JsonProperty("msgHeader")
	private MsgHeader msgHeader;

	@JsonProperty("msgBody")
	private MsgBody msgBody;

	// Getters and Setters
	@Getter
	@Setter
	public static class MsgHeader {
		@JsonProperty("queryTime")
		private String queryTime;

		@JsonProperty("resultCode")
		private int resultCode;

		@JsonProperty("resultMessage")
		private String resultMessage;

		// Getters and Setters
	}

	@Getter
	@Setter
	public static class MsgBody {
		@JsonProperty("busLocationList")
		private List<BusLocation> busLocationList;

		// Getters and Setters
	}

	@Getter
	@Setter
	public static class BusLocation {
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

		// Getters and Setters
	}
}
