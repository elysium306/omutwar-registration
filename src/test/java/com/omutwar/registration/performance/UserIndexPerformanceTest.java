package com.omutwar.registration.performance;

import com.omutwar.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserIndexPerformanceTest {

	@Autowired
	private UserRepository repo;

	@Test
	void testEmailLookupIsFast() {
		long start = System.nanoTime();

		repo.findByEmail("test1@example.com");

		long duration = System.nanoTime() - start;
		assert duration < 5_000_000; // ~5ms
	}
}