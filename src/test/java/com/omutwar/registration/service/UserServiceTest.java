package com.omutwar.registration.service;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repo;

    @Test
    void testSaveAndGetByEmail() {
        User u = new User();
        u.setEmail("service-test@example.com");
        u.setPasswordHash("hash123");
        repo.save(u);

        var found = userService.getUserByEmail("service-test@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("service-test@example.com");
    }
}