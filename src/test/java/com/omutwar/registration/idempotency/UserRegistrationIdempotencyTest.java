package com.omutwar.registration.idempotency;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
class UserRegistrationIdempotencyTest {

    @Autowired
    private UserRepository repo;

    @Test
    void testDuplicateEmailFails() {
        User u1 = new User();
        u1.setEmail("test@example.com");
        u1.setPasswordHash("hash123");
        repo.saveAndFlush(u1);

        User u2 = new User();
        u2.setEmail("test@example.com"); // duplicate
        u2.setPasswordHash("hash456");

        assertThrows(DataIntegrityViolationException.class, () -> repo.saveAndFlush(u2));
    }
}