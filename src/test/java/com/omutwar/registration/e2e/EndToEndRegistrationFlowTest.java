package com.omutwar.registration.e2e;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.domain.Session;
import com.omutwar.registration.service.UserService;
import com.omutwar.registration.service.SessionService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class EndToEndRegistrationFlowTest {

    private final UserService userService = new UserService();
    private final SessionService sessionService = new SessionService();

    @Test
    void testFullRegistrationFlow() throws SQLException {
        // 1. Register user
        User u = new User();
        u.setEmail("flow@example.com");
        u.setPasswordHash("hash");
        u.setFirstName("Flow");
        u.setLastName("Test");
        u.setStatus("ACTIVE");
        u.setCreatedAt(Instant.now());
        u.setUpdatedAt(Instant.now());

        long userId = userService.registerUser(u);
        assertTrue(userService.getUserById(userId).isPresent());

        // 2. Create session
        Session s = new Session();
        s.setUserId(userId);
        s.setToken("abcdefghijklmnopqrstuvwxyz1234567890FLOWTOKEN");
        s.setCreatedAt(Instant.now());
        s.setExpiresAt(Instant.now().plusSeconds(3600));

        long sessionId = sessionService.createSession(s);
        assertTrue(sessionService.getSessionById(sessionId).isPresent());
    }
}