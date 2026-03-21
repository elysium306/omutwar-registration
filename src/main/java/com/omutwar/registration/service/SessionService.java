package com.omutwar.registration.service;

import com.omutwar.registration.domain.Session;
import com.omutwar.registration.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

	private final SessionRepository sessions;

	public SessionService(SessionRepository sessions) {
		this.sessions = sessions;
	}

	public Optional<Session> getByToken(String token) {
		return sessions.findByToken(token);
	}

	public Session save(Session session) {
		return sessions.save(session);
	}
}