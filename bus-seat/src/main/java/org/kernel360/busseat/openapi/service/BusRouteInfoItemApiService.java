package org.kernel360.busseat.openapi.service;

import org.kernel360.busseat.openapi.configuration.BusRouteInfoItemApiProperty;
import org.kernel360.busseat.openapi.configuration.PublicApiServiceKeyProperty;
import org.kernel360.busseat.openapi.dto.BusRouteInfoItemApiBody;
import org.kernel360.busseat.openapi.dto.BusRouteInfoItemApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class BusRouteInfoItemApiService
	extends AbstractOpenApiService<BusRouteInfoItemApiBody, BusRouteInfoItemApiResponse> {
	BusRouteInfoItemApiService(@Autowired BusRouteInfoItemApiProperty busRouteInfoItemApiProperty,
		@Autowired PublicApiServiceKeyProperty publicApiServiceKeyProperty) {
		super(busRouteInfoItemApiProperty, publicApiServiceKeyProperty);
	}

	public BusRouteInfoItemApiResponse request(String routeId) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("routeId", routeId);
		return request(params, BusRouteInfoItemApiResponse.class);
	}
}
