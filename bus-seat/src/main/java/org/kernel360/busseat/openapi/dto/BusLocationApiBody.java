package org.kernel360.busseat.openapi.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JacksonXmlRootElement(localName = "busLocationList")
public class BusLocationApiBody {
	private String endBus;
	private String lowPlate;
	private String plateNo;
	private String plateType;
	private Integer remainSeatCnt;
	private Long routeId;
	private Long stationId;
	private Integer stationSeq;
}
