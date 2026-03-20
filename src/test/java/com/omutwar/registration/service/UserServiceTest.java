package com.omutwar.registration.service;

import com.omutwar.registration.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private final UserService service = new UserService();

    @Test
    void testRegisterUser() throws SQLException {
        User u = new User();
        u.setEmail("service_test@example.com");
        u.setPasswordHash("hash");
        u.setFirstName("Service");
        u.setLastName("Test");
        u.setStatus("ACTIVE");
        u.setCreatedAt(Instant.now());
        u.setUpdatedAt(Instant.now());

        long id = service.registerUser(u);
        assertTrue(service.getUserById(id).isPresent());
    }
}