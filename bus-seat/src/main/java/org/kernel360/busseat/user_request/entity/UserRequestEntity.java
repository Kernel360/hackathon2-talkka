package org.kernel360.busseat.user_request.entity;

import java.sql.Timestamp;

import org.kernel360.busseat.route.entity.RouteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user_collect_request")
public class UserRequestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userCollectRequestId;

	@Column(name = "route_id", nullable = false)
	private Long routeId;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@ManyToOne
	@JoinColumn(name = "route_id", insertable = false, updatable = false)
	private RouteEntity busRoute;
}
