package com.omutwar.registration.repository;

import com.omutwar.registration.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private final UserRepository repo = new UserRepository();

    @Test
    void testInsertAndFindById() throws SQLException {
        User u = new User();
        u.setEmail("test1@example.com");
        u.setPasswordHash("hash123");
        u.setFirstName("John");
        u.setLastName("Doe");
        u.setStatus("ACTIVE");
        u.setCreatedAt(Instant.now());
        u.setUpdatedAt(Instant.now());

        long id = repo.insert(u);
        Optional<User> found = repo.findById(id);

        assertTrue(found.isPresent());
        assertEquals("test1@example.com", found.get().getEmail());
    }

    @Test
    void testFindByEmail() throws SQLException {
        Optional<User> found = repo.findByEmail("test1@example.com");
        assertTrue(found.isPresent());
    }

    @Test
    void testUpdateUser() throws SQLException {
        Optional<User> found = repo.findByEmail("test1@example.com");
        assertTrue(found.isPresent());

        User u = found.get();
        u.setLastName("Updated");

        assertTrue(repo.update(u));

        Optional<User> updated = repo.findById(u.getId());
        assertEquals("Updated", updated.get().getLastName());
    }
}