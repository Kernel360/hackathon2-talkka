package org.kernel360.busseat.route.repository;

import java.util.List;

import org.kernel360.busseat.route.entity.BusRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRouteEntity, String> {
	public List<BusRouteEntity> findByRouteNameContaining(String name);

}
