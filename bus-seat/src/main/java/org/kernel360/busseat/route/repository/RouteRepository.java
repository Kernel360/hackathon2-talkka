package org.kernel360.busseat.route.repository;

import java.util.List;

import org.kernel360.busseat.route.entity.RouteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
	List<RouteEntity> findByRouteNameContaining(String name);

	RouteEntity findByRouteName(String routeName);

	Page<RouteEntity> findByRouteNameContaining(String name, Pageable pageable);
}
