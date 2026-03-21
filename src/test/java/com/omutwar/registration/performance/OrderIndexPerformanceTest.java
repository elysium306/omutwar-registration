package com.omutwar.registration.performance;

import com.omutwar.registration.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class OrderIndexPerformanceTest {

	@Autowired
	private OrderRepository repo;

	@Test
	void testFindByUserIdIsFast() {
		long start = System.nanoTime();

		repo.findByUserId(1L);

		long duration = System.nanoTime() - start;
		assert duration < 5_000_000; // ~5ms
	}
}