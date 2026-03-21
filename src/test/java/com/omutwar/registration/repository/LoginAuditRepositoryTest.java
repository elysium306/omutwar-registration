package com.omutwar.registration.repository;

import com.omutwar.registration.domain.LoginAudit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class LoginAuditRepositoryTest {

	@Autowired
	private LoginAuditRepository repo;

	@Test
	void testSaveAudit() {
		LoginAudit a = new LoginAudit();
		a.setUserId(1L);
		a.setLoginTimestamp(Instant.now());
		a.setIpAddress("127.0.0.1");
		a.setUserAgent("JUnit");
		a.setSuccess(true);

		LoginAudit saved = repo.save(a);

		assertThat(saved.getId()).isNotNull();
	}
}