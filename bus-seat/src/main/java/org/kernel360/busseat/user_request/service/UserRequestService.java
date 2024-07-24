package org.kernel360.busseat.user_request.service;

import java.sql.Timestamp;
import java.util.List;

import org.kernel360.busseat.route.entity.BusRouteEntity;
import org.kernel360.busseat.route.repository.BusRouteRepository;
import org.kernel360.busseat.user_request.dto.UserRequestDto;
import org.kernel360.busseat.user_request.entity.UserRequestEntity;
import org.kernel360.busseat.user_request.repository.UserRequestRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRequestService {

	private final UserRequestRepository userRequestRepository;

	private final BusRouteRepository busRouteRepository;

	public UserRequestDto create(UserRequestDto userRequestDto) {
		final Timestamp now = new Timestamp(System.currentTimeMillis());
		// 추후 검증을 위한 코드(현재는 실질적으로 사용 x)
		final BusRouteEntity busRouteEntity = busRouteRepository.findById(
			String.valueOf(userRequestDto.getRouteId())).orElseThrow(() -> new RuntimeException("해당하는 노선이 없습니다."));
		final var userRequest = userRequestRepository.findById(userRequestDto.getRouteId());
		if (userRequest.isPresent()) {
			return toDto(userRequest.get());
		}
		final var entity = UserRequestEntity.builder()
			.routeId(busRouteEntity.getRouteId())
			.status("OK")
			.createdAt(now)
			.build();
		final var saveEntity = userRequestRepository.save(entity);
		return toDto(saveEntity);
	}

	public List<UserRequestDto> findAll() {
		return userRequestRepository.findAll().stream()
			.map(this::toDto)
			.toList();
	}

	private UserRequestDto toDto(UserRequestEntity userRequestEntity) {
		return UserRequestDto.builder()
			.routeId(userRequestEntity.getRouteId())
			.build();
	}
}
