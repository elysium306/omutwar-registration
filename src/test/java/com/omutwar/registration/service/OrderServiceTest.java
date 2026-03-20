package com.omutwar.registration.service;

import com.omutwar.registration.domain.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private final OrderService service = new OrderService();

    @Test
    void testCreateOrder() throws SQLException {
        Order o = new Order();
        o.setUserId(1);
        o.setTotalAmount(new BigDecimal("25.00"));
        o.setStatus("PENDING");
        o.setCreatedAt(Instant.now());

        long id = service.createOrder(o);
        assertTrue(service.getOrderById(id).isPresent());
    }
}