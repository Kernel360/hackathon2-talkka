package org.kernel360.busseat.schedule.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgBody {
	@JsonProperty("busLocationList")
	private List<BusLocation> busLocationList;
}
