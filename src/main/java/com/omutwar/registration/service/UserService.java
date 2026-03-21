package com.omutwar.registration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.domain.User.UserStatus;
import com.omutwar.registration.dto.UserDto;
import com.omutwar.registration.invitation.Invitation;
import com.omutwar.registration.repository.UserRepository;
import com.omutwar.registration.request.CompleteInviteRequest;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Value("${app.default-user-password}")
	private String defaultUserPassword;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(this::toDto).toList();
	}

	public UserDto getUser(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found: " + id));
		return toDto(user);
	}

	public UserDto createUser(UserCreateRequest req) {
		User user = new User();
		user.setEmail(req.email);
		user.setFirstName(req.firstName);
		user.setLastName(req.lastName);

		// password handling
		String rawPassword = (req.password != null && !req.password.isBlank()) ? req.password : defaultUserPassword;

		user.setPasswordHash(passwordEncoder.encode(rawPassword));

		// default status
		user.setStatus(UserStatus.ACTIVE);

		User saved = userRepository.save(user);
		return toDto(saved);
	}

	public UserDto updateUser(long id, UserDto dto) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found: " + id));

		user.setEmail(dto.email);
		user.setFirstName(dto.firstName);
		user.setLastName(dto.lastName);

		User updated = userRepository.save(user);
		return toDto(updated);
	}

	public void deleteUser(long id) {
		if (!userRepository.existsById(id)) {
			throw new RuntimeException("User not found: " + id);
		}
		userRepository.deleteById(id);
	}

	private UserDto toDto(User user) {
		UserDto dto = new UserDto();
		dto.id = user.getId();
		dto.email = user.getEmail();
		dto.firstName = user.getFirstName();
		dto.lastName = user.getLastName();
		dto.status = user.getStatus().name();
		return dto;
	}

	private User toEntity(UserDto dto) {
		User user = new User();
		user.setEmail(dto.email);
		user.setFirstName(dto.firstName);
		user.setLastName(dto.lastName);

		if (dto.status != null) {
			user.setStatus(UserStatus.parseFrom(dto.status));
		}

		return user;
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found: " + email));
	}

	public boolean validatePassword(String rawPassword, String hashedPassword) {
		return passwordEncoder.matches(rawPassword, hashedPassword);
	}

	public User createFromInvitation(Invitation invitation, CompleteInviteRequest req) {

		User user = new User();
		user.setEmail(invitation.getEmail());
		user.setFirstName(req.getFirstName());
		user.setLastName(req.getLastName());
		user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
		user.setStatus(UserStatus.ACTIVE);

		return userRepository.save(user);
	}

}