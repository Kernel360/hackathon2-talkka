package org.kernel360.busseat.openapi.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;

@Getter
@JacksonXmlRootElement(localName = "response")
public class RouteInfoItemApiResponse implements ApiResponseInterface<RouteInfoItemApiBody> {
	@JacksonXmlProperty(localName = "comMsgHeader")
	private Map<String, String> comMsgHeader;
	@JacksonXmlProperty(localName = "msgHeader")
	private Map<String, String> msgHeader;
	@JacksonXmlProperty(localName = "msgBody")
	private List<RouteInfoItemApiBody> msgBody;
}
