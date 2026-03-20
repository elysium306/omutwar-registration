package com.omutwar.registration.idempotency;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.service.OrderService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class OrderCreationIdempotencyTest {

    private final OrderService service = new OrderService();

    @Test
    void testOrderIdempotency() throws SQLException {
        Order o = new Order();
        o.setUserId(1);
        o.setTotalAmount(new BigDecimal("10.00"));
        o.setStatus("PENDING");
        o.setCreatedAt(Instant.now());

        long id1 = service.createOrder(o);
        long id2 = service.createOrder(o);

        assertEquals(id1, id2);
    }
}