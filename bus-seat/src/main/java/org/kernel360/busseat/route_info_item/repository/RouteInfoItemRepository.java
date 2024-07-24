package org.kernel360.busseat.route_info_item.repository;

import org.kernel360.busseat.route_info_item.entity.RouteInfoItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteInfoItemRepository extends JpaRepository<RouteInfoItemEntity, Long> {

	Page<RouteInfoItemEntity> findByRouteNameContaining(String name, Pageable pageable);
}
