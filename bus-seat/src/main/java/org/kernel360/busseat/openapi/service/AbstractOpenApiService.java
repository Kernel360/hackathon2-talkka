package org.kernel360.busseat.openapi.service;

import java.net.URI;

import org.kernel360.busseat.openapi.configuration.ApiPropertyInterface;
import org.kernel360.busseat.openapi.configuration.PublicOpenApiProperty;
import org.kernel360.busseat.openapi.dto.ApiResponseInterface;
import org.kernel360.busseat.openapi.dto.ResultCode;
import org.kernel360.busseat.openapi.exception.OpenApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractOpenApiService<BODY, RESPONSE_DTO extends ApiResponseInterface<BODY>> {

	private final ApiPropertyInterface apiProperties;
	private final PublicOpenApiProperty publicOpenApiProperty;

	public AbstractOpenApiService(ApiPropertyInterface apiProperties, PublicOpenApiProperty publicOpenApiProperty) {
		this.apiProperties = apiProperties;
		this.publicOpenApiProperty = publicOpenApiProperty;
		log.info("serviceKey: {}", this.publicOpenApiProperty.getRollingKey());
	}

	/**
	 * OpenAPI 요청을 수행합니다.
	 *
	 * ApiResponse 타입의 경우 내부가 어떻게 변경될지 모른다는 특성을 가지고 있음.
	 *
	 * @param params 요청 파라미터
	 * @return OpenAPI 응답
	 */
	public RESPONSE_DTO request(MultiValueMap<String, String> params,
		Class<RESPONSE_DTO> clazz) throws OpenApiException {
		final RestTemplate restTemplate = new RestTemplate();
		final URI uri = this.getOpenApiURI(params);
		log.info("request to {}", uri.toString());
		try {
			final ResponseEntity<RESPONSE_DTO> responseEntity = restTemplate.getForEntity(uri, clazz);
			final RESPONSE_DTO response = responseEntity.getBody();
			if (response == null) {
				log.error("응답이 없습니다. {}", uri.toString());
				throw new OpenApiException("응답이 없습니다.");
			}

			// Result code 가 0 이 아닐 경우 API 요청에서 문제가 있는 것이다.
			final String resultCodeString = response.getMsgHeader().get("resultCode");
			final ResultCode resultCode = ResultCode.fromCode(resultCodeString);

			if (resultCode != ResultCode.SUCCESS) {
				log.error("요청에 대한 응답 오류. {} {}", uri.toString(), resultCode.getMessage());
				throw new OpenApiException(resultCode);
			}
			log.info("response: {}", response);
			return response;
		} catch (OpenApiException e) {
			throw e;
		} catch (RestClientException e) {
			log.error("RestClientException 발생. {}", uri.toString(), e);
			throw new OpenApiException("파싱 과정의 오류", e);
		}
	}

	private URI getOpenApiURI(MultiValueMap<String, String> params) {
		final var builder = new DefaultUriBuilderFactory();
		builder.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
		return builder.builder()
			.scheme("https")
			.host(this.apiProperties.getHost())
			.path(this.apiProperties.getPath())
			.queryParam("serviceKey", this.publicOpenApiProperty.getRollingKey())
			.queryParams(params)
			.build();
	}
}