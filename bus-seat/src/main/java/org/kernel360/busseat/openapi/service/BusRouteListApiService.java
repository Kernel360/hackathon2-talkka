package org.kernel360.busseat.openapi.service;

import org.kernel360.busseat.openapi.configuration.BusRouteListApiProperty;
import org.kernel360.busseat.openapi.configuration.PublicApiServiceKeyProperty;
import org.kernel360.busseat.openapi.dto.BusRouteListBody;
import org.kernel360.busseat.openapi.dto.BusRouteListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class BusRouteListApiService extends AbstractOpenApiService<BusRouteListBody, BusRouteListResponse> {
	public BusRouteListApiService(@Autowired BusRouteListApiProperty apiProperties,
		@Autowired PublicApiServiceKeyProperty publicApiServiceKeyProperty) {
		super(apiProperties, publicApiServiceKeyProperty);
	}

	public BusRouteListResponse request(String keyword) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("keyword", keyword);
		return request(params, BusRouteListResponse.class);
	}
}
