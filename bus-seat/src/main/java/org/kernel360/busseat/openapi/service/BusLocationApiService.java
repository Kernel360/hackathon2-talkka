package org.kernel360.busseat.openapi.service;

import org.kernel360.busseat.openapi.configuration.BusRouteLocationApiProperty;
import org.kernel360.busseat.openapi.configuration.PublicApiServiceKeyProperty;
import org.kernel360.busseat.openapi.dto.BusLocationApiBody;
import org.kernel360.busseat.openapi.dto.BusLocationApiResponse;
import org.kernel360.busseat.user_request.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class BusLocationApiService extends AbstractOpenApiService<BusLocationApiBody, BusLocationApiResponse> {
	public BusLocationApiService(@Autowired BusRouteLocationApiProperty busRouteLocationApiProperty, @Autowired
	PublicApiServiceKeyProperty publicApiServiceKeyProperty) {
		super(busRouteLocationApiProperty, publicApiServiceKeyProperty);
	}

	public BusLocationApiResponse request(UserRequest userRequest) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("routeId", userRequest.getRouteId().toString());
		return request(params, BusLocationApiResponse.class);
	}
}
