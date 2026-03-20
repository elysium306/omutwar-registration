package com.omutwar.registration.controller;

import com.omutwar.registration.dto.LoginResponse;
import com.omutwar.registration.logging.AppLogger;
import com.omutwar.registration.request.LoginRequest;
import com.omutwar.registration.service.AuthenticationService;
import com.omutwar.registration.validation.EmailValidator;
import com.omutwar.registration.validation.ValidationUtils;
import org.slf4j.Logger;

import java.sql.SQLException;

public class LoginController {

    private static final Logger log = AppLogger.get(LoginController.class);
    private final AuthenticationService authService = new AuthenticationService();

    public LoginResponse login(LoginRequest req) throws SQLException {
        log.info("Login request received for {}", req.email);

        ValidationUtils.requireNonEmpty(req.email, "email");
        ValidationUtils.requireNonEmpty(req.password, "password");
        EmailValidator.validate(req.email);

        return authService.login(
                req.email,
                req.password,
                req.ipAddress,
                req.userAgent
        );
    }

    public void logout(long sessionId) throws SQLException {
        log.info("Logout request for session {}", sessionId);
        authService.logout(sessionId);
    }
}
