package com.omutwar.registration.idempotency;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.repository.OrderRepository;

@DataJpaTest
@ActiveProfiles("test")
class OrderCreationIdempotencyTest {

	@Autowired
	private OrderRepository repo;

	@Test
	void testDuplicateIdempotencyKeyFails() {
		Order o1 = new Order();
		o1.setUserId(1L);
		o1.setTotalAmount(new BigDecimal("10.00"));
		o1.setStatus("PENDING");
		o1.setCreatedAt(Instant.now());
		o1.setIdempotencyKey("abc123");

		repo.saveAndFlush(o1);

		Order o2 = new Order();
		o2.setUserId(1L);
		o2.setTotalAmount(new BigDecimal("10.00"));
		o2.setStatus("PENDING");
		o2.setCreatedAt(Instant.now());
		o2.setIdempotencyKey("abc123"); // duplicate

		assertThrows(DataIntegrityViolationException.class, () -> repo.saveAndFlush(o2));
	}
}