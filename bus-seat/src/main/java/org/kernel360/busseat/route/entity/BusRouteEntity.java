package org.kernel360.busseat.route.entity;

import java.sql.Timestamp;
import java.util.Set;

import org.kernel360.busseat.route_info_item.entity.RouteInfoItemEntity;
import org.kernel360.busseat.route_station.entity.BusRouteStationEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "bus_route")
public class BusRouteEntity {
	@Id
	private Long routeId;
	private String routeName;
	private Timestamp createdAt;

	@OneToMany(mappedBy = "busRouteEntity")
	private Set<BusRouteStationEntity> busRouteStationEntity;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "route_id", referencedColumnName = "route_id")
	private RouteInfoItemEntity routeInfoItemEntity;
}
