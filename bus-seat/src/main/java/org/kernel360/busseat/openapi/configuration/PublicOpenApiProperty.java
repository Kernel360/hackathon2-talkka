package org.kernel360.busseat.openapi.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "openapi.public.service-key")
public class PublicOpenApiProperty {

	private List<String> keys;

	private int rollingKeyIndex = 0;

	public String getRollingKey() {
		rollingKeyIndex = (rollingKeyIndex + 1) % keys.size();
		return keys.get(rollingKeyIndex);
	}
}