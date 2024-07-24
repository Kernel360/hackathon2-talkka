package org.kernel360.busseat.openapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "openapi.public.route-info-item")
public class RouteInfoItemApiProperty implements ApiPropertyInterface {
	private String host;
	private String path;
}
