package org.kernel360.busseat.openapi.service;

import org.kernel360.busseat.openapi.configuration.BusRouteStationApiProperty;
import org.kernel360.busseat.openapi.configuration.PublicApiServiceKeyProperty;
import org.kernel360.busseat.openapi.dto.BusRouteStationListBody;
import org.kernel360.busseat.openapi.dto.BusRouteStationListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class BusRouteStationApiService
	extends AbstractOpenApiService<BusRouteStationListBody, BusRouteStationListResponse> {

	public BusRouteStationApiService(@Autowired BusRouteStationApiProperty apiProperties,
		@Autowired PublicApiServiceKeyProperty publicApiServiceKeyProperty) {
		super(apiProperties, publicApiServiceKeyProperty);
	}

	public BusRouteStationListResponse request(String routeId) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("routeId", routeId);
		return request(params, BusRouteStationListResponse.class);
	}
}
