package org.kernel360.busseat.route_info_item.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "bus_route_info_item")
public class RouteInfoItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bus_route_info_item_id")
	private Long busRouteInfoItemId; // 노선 상세 아이디

	@Column(name = "route_id", nullable = false)
	private Long routeId;           // 노선 아이디

	@Column(name = "route_name", nullable = false, length = 100)
	private String routeName;       // 노선 번호

	@Column(name = "route_type_cd", nullable = false)
	private int routeTypeCd;        // 노선 유형 코드

	@Column(name = "route_type_name", nullable = false, length = 50)
	private String routeTypeName;   // 노선 유형명

	@Column(name = "company_id", nullable = false, length = 20)
	private String companyId;       // 운수업체 아이디

	@Column(name = "company_name", nullable = false, length = 50)
	private String companyName;     // 운수업체명

	@Column(name = "company_tel", length = 15)
	private String companyTel;      // 운수업체 전화번호

	@Column(name = "district_cd", nullable = false)
	private int districtCd;         // 관할 지역 코드

	@Column(name = "up_first_time", nullable = false, length = 20)
	private String upFirstTime;     // 평일 기점 첫차 시간

	@Column(name = "up_last_time", nullable = false, length = 20)
	private String upLastTime;      // 평일 기점 막차 시간

	@Column(name = "down_first_time", nullable = false, length = 20)
	private String downFirstTime;   // 평일 종점 첫차 시간

	@Column(name = "down_last_time", nullable = false, length = 20)
	private String downLastTime;    // 평일 종점 막차 시간

	@Column(name = "start_mobile_no", length = 10)
	private String startMobileNo;   // 기점 정류소 번호

	@Column(name = "start_station_id", nullable = false)
	private Long startStationId;    // 기점 정류소 아이디

	@Column(name = "start_station_name", nullable = false, length = 100)
	private String startStationName; // 기점 정류소명

	@Column(name = "end_station_id", nullable = false)
	private Long endStationId;      // 종점 정류소 아이디

	@Column(name = "end_station_name", nullable = false, length = 100)
	private String endStationName;  // 종점 정류소명

	@Column(name = "region_name", length = 100)
	private String regionName;      // 지역명

	@Column(name = "peek_alloc", nullable = false)
	private int peekAlloc;          // 평일 최소 배차 시간

	@Column(name = "n_peek_alloc", nullable = false)
	private int nPeekAlloc;         // 평일 최대 배차 시간
}
