package com.omutwar.registration.repository;

import com.omutwar.registration.domain.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryTest {

    private final OrderRepository repo = new OrderRepository();

    @Test
    void testInsertOrder() throws SQLException {
        Order o = new Order();
        o.setUserId(1);
        o.setTotalAmount(new BigDecimal("49.99"));
        o.setStatus("PENDING");
        o.setCreatedAt(Instant.now());

        long id = repo.insert(o);
        assertTrue(repo.findById(id).isPresent());
    }

    @Test
    void testFindByUserId() throws SQLException {
        List<Order> orders = repo.findByUserId(1);
        assertNotNull(orders);
    }
}