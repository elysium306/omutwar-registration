package com.omutwar.registration.security;

import com.omutwar.registration.domain.Session;
import com.omutwar.registration.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class SessionSecurityService {

	private final SessionRepository sessionRepository;

	public SessionSecurityService(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	public Session createSecureSession(long userId) {
		Session s = new Session();
		s.setUserId(userId);
		s.setToken(TokenGenerator.sessionToken());
		s.setCreatedAt(Instant.now());
		s.setExpiresAt(Instant.now().plusSeconds(3600)); // 1 hour

		return sessionRepository.save(s);
	}

	public Optional<Session> validateSession(String token) {
		Optional<Session> s = sessionRepository.findByToken(token);

		if (s.isEmpty())
			return Optional.empty();
		if (s.get().getExpiresAt().isBefore(Instant.now()))
			return Optional.empty();
		if (s.get().getRevokedAt() != null)
			return Optional.empty();

		return s;
	}

	public void revokeSession(long sessionId) {
		sessionRepository.findById(sessionId).ifPresent(session -> {
			session.setRevokedAt(Instant.now());
			sessionRepository.save(session);
		});
	}
}