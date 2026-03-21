package com.omutwar.registration.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omutwar.registration.dto.UserDto;
import com.omutwar.registration.service.UserCreateRequest;
import com.omutwar.registration.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable long id) {
		return userService.getUser(id);
	}

	@PostMapping
	public UserDto createUser(@Valid @RequestBody UserCreateRequest req) {
		return userService.createUser(req);
	}

	@PutMapping("/{id}")
	public UserDto updateUser(@PathVariable long id, @Valid @RequestBody UserDto dto) {
		dto.id = id;
		return userService.updateUser(id, dto);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}
}