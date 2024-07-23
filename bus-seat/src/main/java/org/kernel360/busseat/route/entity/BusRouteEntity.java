package org.kernel360.busseat.route.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

}
