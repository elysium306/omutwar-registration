package com.omutwar.registration.idempotency;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.repository.OrderRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
class OrderConstraintValidationTest {

	@Autowired
	private OrderRepository repo;

	@Test
	void testNegativeAmountFails() {
		Order o = new Order();
		o.setUserId(1L);
		o.setTotalAmount(new BigDecimal("-5.00"));
		o.setStatus("PENDING");
		o.setCreatedAt(Instant.now());
		o.setIdempotencyKey("unique-key-123");

		assertThrows(ConstraintViolationException.class, () -> repo.saveAndFlush(o));
	}
}