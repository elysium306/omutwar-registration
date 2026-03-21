package com.omutwar.registration.repository;

import com.omutwar.registration.domain.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class SessionRepositoryTest {

	@Autowired
	private SessionRepository repo;

	@Test
	void testSaveAndFindByToken() {
		Session s = new Session();
		s.setUserId(1L);
		s.setToken("abc123");
		s.setCreatedAt(Instant.now());
		s.setExpiresAt(Instant.now().plusSeconds(3600));

		repo.save(s);

		assertThat(repo.findByToken("abc123")).isPresent();
	}
}