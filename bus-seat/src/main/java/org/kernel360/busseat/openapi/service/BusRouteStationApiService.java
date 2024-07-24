package org.kernel360.busseat.openapi.service;

import org.kernel360.busseat.openapi.configuration.BusRouteStationApiProperty;
import org.kernel360.busseat.openapi.configuration.PublicOpenApiProperty;
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
		@Autowired PublicOpenApiProperty publicOpenApiProperty) {
		super(apiProperties, publicOpenApiProperty);
	}

	public MultiValueMap<String, String> getQueryParameters(Long routeId) {
		final MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
		queryParameters.add("routeId", routeId.toString());
		return queryParameters;
	}
}
