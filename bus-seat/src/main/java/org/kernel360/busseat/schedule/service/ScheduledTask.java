package org.kernel360.busseat.schedule.service;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Map;

import org.kernel360.busseat.schedule.ApiProperties;
import org.kernel360.busseat.schedule.dto.BusLocation;
import org.kernel360.busseat.schedule.dto.BusLocationDto;
import org.kernel360.busseat.schedule.entity.BusRouteLocationEntity;
import org.kernel360.busseat.schedule.repository.BusRouteLocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableScheduling
public class ScheduledTask {

	private final ApiProperties apiProperties;
	private final BusRouteLocationRepository busLocationRepository;

	@Scheduled(fixedRate = 60000) // 180000 밀리초 = 3분
	public void sendScheduledRequest() {
		// API 호출을 위한 파라미터 준비
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("routeId", "233000031"); // 여기에 기본 또는 필요한 파라미터를 추가

		// request 메서드를 사용하여 API 호출
		String path = "/6410000/buslocationservice/getBusLocationList";
		String encodedKey = apiProperties.getServiceKey(); // serviceKey가 올바르게 설정되었는지 확인

		// request 메서드를 사용하여 요청 수행
		Map<String, Object> response = request(path, encodedKey, params, Map.class);

		// 요청이 보내졌음을 로그로 남김
		log.info("Scheduled request sent. Response: {}", response);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			BusLocationDto busLocationDto = objectMapper.convertValue(response, BusLocationDto.class);
			for (BusLocation busLocation : busLocationDto.getMsgBody().getBusLocationList()) {
				BusRouteLocationEntity busLocationEntity = new BusRouteLocationEntity();
				busLocationEntity.setEndBus(busLocation.getEndBus());
				busLocationEntity.setStationId(busLocation.getStationId());
				busLocationEntity.setPlateNo(busLocation.getPlateNo());
				busLocationEntity.setPlateType(busLocation.getPlateType());
				busLocationEntity.setLowPlate(busLocation.getLowPlate());
				busLocationEntity.setRouteId(busLocation.getRouteId());
				busLocationEntity.setStationSeq(busLocation.getStationSeq());
				busLocationEntity.setRemainSeatCount(busLocation.getRemainSeatCount());
				busLocationEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				busLocationRepository.save(busLocationEntity);
				log.info("bus_route_location : {}", busLocation.getEndBus());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

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