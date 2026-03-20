package com.omutwar.registration.service;

import com.omutwar.registration.domain.Session;
import com.omutwar.registration.repository.SessionRepository;

import java.sql.SQLException;
import java.util.Optional;

public class SessionService {

    private final SessionRepository repo = new SessionRepository();

    public long createSession(Session s) throws SQLException {
        return repo.insert(s);
    }

    public Optional<Session> getSessionById(long id) throws SQLException {
        return repo.findById(id);
    }
}