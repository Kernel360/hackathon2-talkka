package org.kernel360.busseat.openapi.dto;

public enum ResultCode {
	SUCCESS(0, "정상적으로 처리되었습니다."),
	SYSTEM_ERROR(1, "시스템 에러가 발생하였습니다."),
	MISSING_PARAMETER(2, "필수 요청 Parameter 가 존재하지 않습니다."),
	INVALID_PARAMETER(3, "필수 요청 Parameter 가 잘못되었습니다."),
	NO_RESULT(4, "결과가 존재하지 않습니다."),
	MISSING_AUTH_KEY(5, "필수 요청 Parameter(인증키) 가 존재하지 않습니다."),
	UNREGISTERED_KEY(6, "등록되지 않은 키입니다."),
	DISABLED_KEY(7, "사용할 수 없는(등록은 되었으나, 일시적으로 사용 중지된) 키입니다."),
	REQUEST_LIMIT_EXCEEDED(8, "요청 제한을 초과하였습니다."),
	INVALID_LOCATION_REQUEST(20, "잘못된 위치로 요청하였습니다. 위경도 좌표값이 정확한지 확인하십시오."),
	INVALID_ROUTE_NUMBER(21, "노선번호는 1자리 이상 입력하세요."),
	INVALID_STATION_NAME_OR_NUMBER(22, "정류소명/번호는 1자리 이상 입력하세요."),
	NO_BUS_ARRIVAL_INFO(23, "버스 도착 정보가 존재하지 않습니다."),
	INVALID_START_STATION_ID(31, "존재하지 않는 출발 정류소 아이디(ID)/번호입니다."),
	INVALID_DESTINATION_STATION_ID(32, "존재하지 않는 도착 정류소 아이디(ID)/번호입니다."),
	API_SERVICE_PREPARING(99, "API 서비스 준비중입니다.");

	private final int code;
	private final String message;

	ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static ResultCode fromCode(int code) {
		for (ResultCode resultCode : values()) {
			if (resultCode.getCode() == code) {
				return resultCode;
			}
		}
		return SYSTEM_ERROR; // Default to system error for unknown codes
	}
}
