package com.omutwar.postgres.mapper;

import com.omutwar.postgres.dto.UserDto;
import com.omutwar.registration.domain.User;

public final class UserMapper {

	private UserMapper() {
	}

	public static UserDto toDto(User u) {
		UserDto dto = new UserDto();
		dto.id = u.getId();
		dto.email = u.getEmail();
		dto.firstName = u.getFirstName();
		dto.lastName = u.getLastName();
		dto.status = u.getStatus();
		return dto;
	}
}