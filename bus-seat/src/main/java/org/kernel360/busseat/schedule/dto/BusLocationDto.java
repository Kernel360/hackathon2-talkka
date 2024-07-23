package org.kernel360.busseat.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class BusLocationDto {
	@JsonProperty("comMsgHeader")
	private Object comMsgHeader;

	@JsonProperty("msgHeader")
	private MsgHeader msgHeader;

	@JsonProperty("msgBody")
	private MsgBody msgBody;

}

