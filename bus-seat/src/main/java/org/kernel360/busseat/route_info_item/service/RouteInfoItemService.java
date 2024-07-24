package org.kernel360.busseat.route_info_item.service;

import org.kernel360.busseat.openapi.dto.RouteInfoItemApiBody;
import org.kernel360.busseat.route_info_item.entity.RouteInfoItemEntity;
import org.kernel360.busseat.route_info_item.repository.RouteInfoItemRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteInfoItemService {
	private final RouteInfoItemRepository routeInfoItemRepository;

	public RouteInfoItemEntity toEntity(RouteInfoItemApiBody routeInfoItemApiBody) {
		return RouteInfoItemEntity.builder()
			.routeId(routeInfoItemApiBody.getRouteId())
			.routeName(routeInfoItemApiBody.getRouteName())
			.regionName(routeInfoItemApiBody.getRegionName())
			.startStationName(routeInfoItemApiBody.getStartStationName())
			.endStationName(routeInfoItemApiBody.getEndStationName())
			.endStationId(routeInfoItemApiBody.getEndStationId())
			.startStationId(routeInfoItemApiBody.getStartStationId())
			.startMobileNo(routeInfoItemApiBody.getStartMobileNo())
			.companyId(routeInfoItemApiBody.getCompanyId())
			.companyName(routeInfoItemApiBody.getCompanyName())
			.companyTel(routeInfoItemApiBody.getCompanyTel())
			.districtCd(routeInfoItemApiBody.getDistrictCd())
			.downFirstTime(routeInfoItemApiBody.getDownFirstTime())
			.downLastTime(routeInfoItemApiBody.getDownLastTime())
			.upFirstTime(routeInfoItemApiBody.getUpFirstTime())
			.upLastTime(routeInfoItemApiBody.getUpLastTime())
			.routeTypeCd(routeInfoItemApiBody.getRouteTypeCd())
			.routeTypeName(routeInfoItemApiBody.getRouteTypeName())
			.build();
	}
}
