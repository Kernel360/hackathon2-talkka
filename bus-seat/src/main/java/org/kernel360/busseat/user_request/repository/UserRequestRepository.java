package org.kernel360.busseat.user_request.repository;

import org.kernel360.busseat.user_request.entity.UserRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepository extends JpaRepository<UserRequestEntity, Long> {
}
