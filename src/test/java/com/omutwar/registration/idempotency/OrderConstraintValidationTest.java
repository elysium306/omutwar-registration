package com.omutwar.registration.idempotency;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class OrderConstraintValidationTest {

    private final OrderRepository repo = new OrderRepository();

    @Test
    void testNegativeAmountFails() {
        Order o = new Order();
        o.setUserId(1);
        o.setTotalAmount(new BigDecimal("-5.00"));
        o.setStatus("PENDING");
        o.setCreatedAt(Instant.now());

        assertThrows(SQLException.class, () -> repo.insert(o));
    }
}