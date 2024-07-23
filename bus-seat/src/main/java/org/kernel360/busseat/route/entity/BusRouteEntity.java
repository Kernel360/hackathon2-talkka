package org.kernel360.busseat.route.entity;

import java.sql.Timestamp;
import java.util.Set;

import org.kernel360.busseat.route_station.entity.BusRouteStationEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
}
