package org.kernel360.busseat.openapi.exception;

import org.kernel360.busseat.openapi.dto.ResultCode;

public class OpenApiException extends RuntimeException {
	public OpenApiException(String message) {
		super(message);
	}

	public OpenApiException(ResultCode resultCode) {
		super(resultCode.getMessage());
	}

	public OpenApiException(String message, Throwable cause) {
		super(message, cause);
	}
}
