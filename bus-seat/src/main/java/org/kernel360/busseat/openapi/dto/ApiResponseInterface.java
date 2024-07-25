package org.kernel360.busseat.openapi.dto;

import java.util.List;
import java.util.Map;

public interface ApiResponseInterface<T> {
	Map<String, String> getComMsgHeader();

	Map<String, String> getMsgHeader();

	List<T> getMsgBody();
}
