package com.omutwar.registration.repository;

import com.omutwar.registration.domain.Session;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class SessionRepositoryTest {

    private final SessionRepository repo = new SessionRepository();

    @Test
    void testInsertSession() throws SQLException {
        Session s = new Session();
        s.setUserId(1);
        s.setToken("abcdefghijklmnopqrstuvwxyz1234567890TOKEN");
        s.setCreatedAt(Instant.now());
        s.setExpiresAt(Instant.now().plusSeconds(3600));

        long id = repo.insert(s);
        assertTrue(repo.findById(id).isPresent());
    }
}