package org.kernel360.busseat.openapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "openapi.public.route-location")
public class BusRouteLocationApiProperty implements ApiPropertyInterface {
	private String host;
	private String path;
}
