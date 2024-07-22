package org.kernel360.busseat.user_request.service;

import java.sql.Timestamp;

import org.kernel360.busseat.user_request.dto.UserRequestDto;
import org.kernel360.busseat.user_request.entity.UserRequestEntity;
import org.kernel360.busseat.user_request.repository.UserRequestRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRequestService {

	private final UserRequestRepository userRequestRepository;

	public UserRequestDto create(Long routeId, String routeName) {
		Timestamp now = new Timestamp(System.currentTimeMillis());

		var entity = UserRequestEntity.builder()
			.routeId(routeId)
			.routeName(routeName)
			.status("OK")
			.createdAt(now)
			.build();

		var saveEntity = userRequestRepository.save(entity);

		return toDto(saveEntity);
	}

	private UserRequestDto toDto(UserRequestEntity userRequestEntity) {
		return UserRequestDto.builder()
			.routeId(userRequestEntity.getRouteId())
			.build();
	}
}
