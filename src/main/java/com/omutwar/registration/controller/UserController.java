package com.omutwar.registration.controller;

import java.sql.SQLException;
import java.time.Instant;

import org.slf4j.Logger;

import com.omutwar.postgres.dto.UserDto;
import com.omutwar.postgres.mapper.UserMapper;
import com.omutwar.registration.domain.User;
import com.omutwar.registration.exception.NotFoundException;
import com.omutwar.registration.logging.AppLogger;
import com.omutwar.registration.request.UserRegistrationRequest;
import com.omutwar.registration.security.PasswordHasher;
import com.omutwar.registration.service.UserService;
import com.omutwar.registration.validation.EmailValidator;
import com.omutwar.registration.validation.ValidationUtils;

public class UserController {

	private static final Logger log = AppLogger.get(UserController.class);
	private final UserService service = new UserService();

	public UserDto getUser(long id) throws SQLException {
		log.info("Fetching user with id={}", id);

		return service.getUserById(id).map(UserMapper::toDto)
				.orElseThrow(() -> new NotFoundException("User not found: " + id));
	}

	public long registerUser(UserRegistrationRequest req) throws SQLException {
		log.info("Registering new user: {}", req.email);

		ValidationUtils.requireNonEmpty(req.email, "email");
		ValidationUtils.requireNonEmpty(req.password, "password");
		ValidationUtils.requireNonEmpty(req.firstName, "firstName");
		ValidationUtils.requireNonEmpty(req.lastName, "lastName");
		EmailValidator.validate(req.email);

		User u = new User();
		u.setEmail(req.email.trim());
		u.setPasswordHash(PasswordHasher.hash(req.password)); // <-- HASH HERE
		u.setFirstName(req.firstName.trim());
		u.setLastName(req.lastName.trim());
		u.setStatus("ACTIVE");
		u.setCreatedAt(Instant.now());
		u.setUpdatedAt(Instant.now());

		long id = service.registerUser(u);
		log.info("User registered successfully with id={}", id);

		return id;
	}

}