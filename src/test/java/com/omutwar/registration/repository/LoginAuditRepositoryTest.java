package com.omutwar.registration.repository;

import com.omutwar.registration.domain.LoginAudit;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class LoginAuditRepositoryTest {

	private final LoginAuditRepository repo = new LoginAuditRepository();

	@Test
	void testInsertAuditRecord() throws SQLException {
		LoginAudit a = new LoginAudit();
		a.setUserId(1L);
		a.setLoginTimestamp(Instant.now());
		a.setIpAddress("127.0.0.1");
		a.setUserAgent("JUnit Test");
		a.setSuccess(true);

		long id = repo.insert(a);
		assertTrue(repo.findById(id).isPresent());
	}
}