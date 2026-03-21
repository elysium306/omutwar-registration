package com.omutwar.registration.repository;

import com.omutwar.registration.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class OrderRepositoryTest {

    @Autowired
    private OrderRepository repo;

    @Test
    void testSaveOrder() {
        Order o = new Order();
        o.setUserId(1L);
        o.setTotalAmount(new BigDecimal("10.00"));
        o.setStatus("PENDING");
        o.setCreatedAt(Instant.now());

        Order saved = repo.save(o);

        assertThat(saved.getId()).isNotNull();
    }
}