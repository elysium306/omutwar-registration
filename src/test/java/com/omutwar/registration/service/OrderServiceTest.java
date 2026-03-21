package com.omutwar.registration.service;

import com.omutwar.registration.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@Test
	void testCreateOrder() {
		Order o = new Order();
		o.setUserId(1L);
		o.setTotalAmount(new BigDecimal("10.00"));
		o.setStatus("PENDING");
		o.setCreatedAt(Instant.now());
		o.setIdempotencyKey("svc-order-1");

		Order saved = orderService.save(o);

		assertThat(saved.getId()).isNotNull();
	}
}