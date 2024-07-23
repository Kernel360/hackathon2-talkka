package org.kernel360.busseat.openapi.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties("api")
public class ApiProperties {
	private String serviceKey;
}
