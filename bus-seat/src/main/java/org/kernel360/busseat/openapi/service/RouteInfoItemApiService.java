package org.kernel360.busseat.openapi.service;

import org.kernel360.busseat.openapi.configuration.PublicOpenApiProperty;
import org.kernel360.busseat.openapi.configuration.RouteInfoItemApiProperty;
import org.kernel360.busseat.openapi.dto.RouteInfoItemApiBody;
import org.kernel360.busseat.openapi.dto.RouteInfoItemApiResponse;
import org.kernel360.busseat.user_request.dto.UserRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class RouteInfoItemApiService extends AbstractOpenApiService<RouteInfoItemApiBody, RouteInfoItemApiResponse> {
	RouteInfoItemApiService(RouteInfoItemApiProperty routeInfoItemApiProperty,
		PublicOpenApiProperty publicOpenApiProperty) {
		super(routeInfoItemApiProperty, publicOpenApiProperty);
	}

	@Override
	public MultiValueMap<String, String> getQueryParameters(UserRequestDto userRequestDto) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("routeId", userRequestDto.getRouteId().toString());
		return params;
	}
}
