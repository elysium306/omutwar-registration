package com.omutwar.registration.security;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;

import com.omutwar.registration.domain.Session;
import com.omutwar.registration.repository.SessionRepository;

public class SessionSecurityService {

	final SessionRepository repo = new SessionRepository();

	public Session createSecureSession(long userId) throws SQLException {
		Session s = new Session();
		s.setUserId(userId);
		s.setToken(TokenGenerator.sessionToken());
		s.setCreatedAt(Instant.now());
		s.setExpiresAt(Instant.now().plusSeconds(3600));
		s.setIdempotencyKey(IdempotencyKeyGenerator.generate());

		long id = repo.insert(s);
		s.setId(id);
		return s;
	}

	public Optional<Session> findSessionByToken(String token) throws SQLException {
		return repo.findByToken(token); // <-- FIX
	}

	public void revokeSession(long sessionId) throws SQLException {
		repo.revoke(sessionId);
	}
}
