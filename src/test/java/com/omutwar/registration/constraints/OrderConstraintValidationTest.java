package com.omutwar.registration.constraints;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.repository.OrderRepository;

import jakarta.validation.ConstraintViolationException;

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

		assertThrows(ConstraintViolationException.class, () -> repo.saveAndFlush(o));
	}
}