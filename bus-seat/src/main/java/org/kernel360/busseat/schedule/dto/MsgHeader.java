package org.kernel360.busseat.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgHeader {
	@JsonProperty("queryTime")
	private String queryTime;

	@JsonProperty("resultCode")
	private int resultCode;

	@JsonProperty("resultMessage")
	private String resultMessage;

	// Getters and Setters
}
