package org.kernel360.busseat.openapi.service;

import org.kernel360.busseat.openapi.configuration.PublicOpenApiProperty;
import org.kernel360.busseat.openapi.configuration.RouteLocationApiProperty;
import org.kernel360.busseat.openapi.dto.BusLocationApiBody;
import org.kernel360.busseat.openapi.dto.BusLocationApiResponse;
import org.kernel360.busseat.user_request.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class BusLocationApiService extends AbstractOpenApiService<BusLocationApiBody, BusLocationApiResponse> {
	public BusLocationApiService(@Autowired RouteLocationApiProperty routeLocationApiProperty, @Autowired
	PublicOpenApiProperty publicOpenApiProperty) {
		super(routeLocationApiProperty, publicOpenApiProperty);
	}

	public MultiValueMap<String, String> getQueryParameters(UserRequestDto userRequestDto) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("routeId", userRequestDto.getRouteId().toString());
		return params;
	}
}
