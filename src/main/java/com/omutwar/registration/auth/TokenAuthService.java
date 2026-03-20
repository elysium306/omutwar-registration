package com.omutwar.registration.auth;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;

import com.omutwar.registration.domain.Session;
import com.omutwar.registration.security.SessionSecurityService;

public class TokenAuthService {

	private final SessionSecurityService sessionSecurity = new SessionSecurityService();

	public Optional<AuthContext> authenticate(String token) throws SQLException {
		Optional<Session> s = sessionSecurity.findSessionByToken(token);

		if (s.isEmpty())
			return Optional.empty();
		if (s.get().getRevokedAt() != null)
			return Optional.empty();
		if (s.get().getExpiresAt().isBefore(Instant.now()))
			return Optional.empty();

		return Optional.of(new AuthContext(s.get().getUserId()));
	}
}