package org.kernel360.busseat.route_info_item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.kernel360.busseat.common.dto.Pagination;
import org.kernel360.busseat.common.dto.PaginationDto;
import org.kernel360.busseat.openapi.dto.RouteInfoItemApiBody;
import org.kernel360.busseat.route_info_item.dto.RouteInfoItemDto;
import org.kernel360.busseat.route_info_item.entity.RouteInfoItemEntity;
import org.kernel360.busseat.route_info_item.repository.RouteInfoItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	public PaginationDto<RouteInfoItemDto> getRouteByName(String routeName, int pageNumber, int pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<RouteInfoItemEntity> routePage = routeInfoItemRepository.findByRouteNameContaining(routeName, pageable);

		List<RouteInfoItemDto> routeInfoItemDtos = routePage.stream()
			.map(entity -> RouteInfoItemDto.builder()
				.routeId(entity.getRouteId())
				.routeName(entity.getRouteName())
				.stationStart(entity.getStartStationName())
				.stationEnd(entity.getEndStationName())
				.build())
			.collect(Collectors.toList());

		Pagination pagination = Pagination.builder()
			.currentPage((long)pageNumber)
			.totalPage((long)routePage.getTotalPages())
			.build();

		return PaginationDto.<RouteInfoItemDto>builder()
			.pagination(pagination)
			.data(routeInfoItemDtos)
			.build();

	}

}
