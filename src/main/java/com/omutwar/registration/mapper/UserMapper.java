package com.omutwar.registration.mapper;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.dto.UserDto;

public final class UserMapper {

	private UserMapper() {
	}

	public static UserDto toDto(User u) {
		UserDto dto = new UserDto();
		dto.id = u.getId();
		dto.email = u.getEmail();
		dto.firstName = u.getFirstName();
		dto.lastName = u.getLastName();
		dto.status = u.getStatus() != null ? u.getStatus().name() : null;
		return dto;
	}
}