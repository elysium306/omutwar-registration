package com.omutwar.registration.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omutwar.registration.dto.LoginResponse;
import com.omutwar.registration.request.LoginRequest;
import com.omutwar.registration.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class LoginController {

	private final AuthenticationService authService;

	public LoginController(AuthenticationService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest req, HttpServletRequest http) {
		String ip = http.getRemoteAddr();
		String ua = http.getHeader("User-Agent");
		return authService.login(req.email, req.password, ip, ua);
	}
}