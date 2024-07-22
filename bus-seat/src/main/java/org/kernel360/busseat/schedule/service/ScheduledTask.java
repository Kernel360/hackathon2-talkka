package org.kernel360.busseat.schedule.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor

public class ScheduledTask {

	public static <T> T request(String path, String encodedKey,
		MultiValueMap<String, String> params, Class<T> clazz) {
		final RestTemplate restTemplate = new RestTemplate();
		final var builder = new DefaultUriBuilderFactory();
		builder.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

		final String uriString = builder.builder()
			.scheme("https")
			.host("apis.data.go.kr")
			.path(path)
			.queryParam("serviceKey", encodedKey)
			.queryParams(params)
			.build()
			.toString();
		final URI uri = URI.create(uriString);

		ResponseEntity<T> response = restTemplate.getForEntity(uri, clazz);
		return response.getBody();
	}
}
