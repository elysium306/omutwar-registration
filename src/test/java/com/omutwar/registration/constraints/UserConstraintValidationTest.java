package com.omutwar.registration.constraints;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class UserConstraintValidationTest {

    private final UserRepository repo = new UserRepository();

    @Test
    void testInvalidStatusFails() {
        User u = new User();
        u.setEmail("badstatus@example.com");
        u.setPasswordHash("hash");
        u.setFirstName("Bad");
        u.setLastName("Status");
        u.setStatus("INVALID");
        u.setCreatedAt(Instant.now());
        u.setUpdatedAt(Instant.now());

        assertThrows(SQLException.class, () -> repo.insert(u));
    }
}