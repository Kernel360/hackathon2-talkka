package org.kernel360.busseat.openapi.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 기본 형태 규정. 실제 API 에 대해서는 별도의 클래스를 사용해야함
 * @param <T>
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "response")
public class ApiResponse<T> implements ApiResponseInterface<T> {
	@JacksonXmlProperty(localName = "comMsgHeader")
	private Map<String, String> comMsgHeader;
	@JacksonXmlProperty(localName = "msgHeader")
	private Map<String, String> msgHeader;
	@JacksonXmlProperty(localName = "msgBody")
	private List<T> msgBody;
}
