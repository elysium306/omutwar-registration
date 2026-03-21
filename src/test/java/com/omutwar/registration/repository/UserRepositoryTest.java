package com.omutwar.registration.repository;

import com.omutwar.registration.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Test
    void testSaveAndFindByEmail() {
        User u = new User();
        u.setEmail("test@example.com");
        u.setPasswordHash("hash123");

        repo.save(u);

        Optional<User> found = repo.findByEmail("test@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("test@example.com");
    }
}