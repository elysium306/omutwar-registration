package com.omutwar.registration.performance;

import com.omutwar.registration.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class OrderIndexPerformanceTest {

    private final OrderRepository repo = new OrderRepository();

    @Test
    void testUserOrderLookupIsFast() throws SQLException {
        long start = System.nanoTime();
        repo.findByUserId(1);
        long duration = System.nanoTime() - start;

        assertTrue(duration < 5_000_000, "Order lookup should be fast with composite index");
    }
}