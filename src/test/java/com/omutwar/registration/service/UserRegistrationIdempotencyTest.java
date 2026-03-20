package com.omutwar.registration.service;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.service.UserService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationIdempotencyTest {

    private final UserService service = new UserService();

    @Test
    void testIdempotentUserRegistration() throws SQLException {
        User u = new User();
        u.setEmail("idempotent@example.com");
        u.setPasswordHash("hash");
        u.setFirstName("Idem");
        u.setLastName("Potent");
        u.setStatus("ACTIVE");
        u.setCreatedAt(Instant.now());
        u.setUpdatedAt(Instant.now());

        long id1 = service.registerUser(u);

        // Retry same request
        long id2 = service.registerUser(u);

        assertEquals(id1, id2, "Idempotent registration should return same user ID");
    }
}