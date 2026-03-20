package com.omutwar.registration.performance;

import com.omutwar.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserIndexPerformanceTest {

    private final UserRepository repo = new UserRepository();

    @Test
    void testEmailLookupIsFast() throws SQLException {
        long start = System.nanoTime();
        repo.findByEmail("test1@example.com");
        long duration = System.nanoTime() - start;

        assertTrue(duration < 5_000_000, "Email lookup should be fast with index");
    }
}