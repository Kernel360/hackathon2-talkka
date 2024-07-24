package org.kernel360.busseat.openapi.service;

import org.kernel360.busseat.openapi.configuration.PublicOpenApiProperty;
import org.kernel360.busseat.openapi.configuration.RouteInfoItemApiProperty;
import org.kernel360.busseat.openapi.dto.RouteInfoItemApiBody;
import org.kernel360.busseat.openapi.dto.RouteInfoItemApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class RouteInfoItemApiService extends AbstractOpenApiService<RouteInfoItemApiBody, RouteInfoItemApiResponse> {
	RouteInfoItemApiService(RouteInfoItemApiProperty routeInfoItemApiProperty,
		PublicOpenApiProperty publicOpenApiProperty) {
		super(routeInfoItemApiProperty, publicOpenApiProperty);
	}

	public RouteInfoItemApiResponse request(String routeId) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("routeId", routeId);
		return request(params, RouteInfoItemApiResponse.class);
	}
}
