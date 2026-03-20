package com.omutwar.registration.service;

import com.omutwar.registration.domain.LoginAudit;
import com.omutwar.registration.domain.Session;
import com.omutwar.registration.domain.User;
import com.omutwar.registration.dto.LoginResponse;
import com.omutwar.registration.exception.NotFoundException;
import com.omutwar.registration.exception.ValidationException;
import com.omutwar.registration.logging.AppLogger;
import com.omutwar.registration.repository.LoginAuditRepository;
import com.omutwar.registration.security.PasswordHasher;
import com.omutwar.registration.security.SessionSecurityService;
import org.slf4j.Logger;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;

public class AuthenticationService {

	private static final Logger log = AppLogger.get(AuthenticationService.class);

	private final UserService userService = new UserService();
	private final SessionSecurityService sessionSecurity = new SessionSecurityService();
	private final LoginAuditRepository auditRepo = new LoginAuditRepository();

	public LoginResponse login(String email, String password, String ip, String userAgent) throws SQLException {
		log.info("Attempting login for {}", email);

		Optional<User> userOpt = userService.getUserByEmail(email);

		if (userOpt.isEmpty()) {
			recordAudit(null, ip, userAgent, false);
			throw new NotFoundException("Invalid credentials");
		}

		User user = userOpt.get();

		if (!PasswordHasher.verify(password, user.getPasswordHash())) {
			recordAudit(user.getId(), ip, userAgent, false);
			throw new ValidationException("Invalid credentials");
		}

		Session session = sessionSecurity.createSecureSession(user.getId());

		recordAudit(user.getId(), ip, userAgent, true);

		LoginResponse resp = new LoginResponse();
		resp.userId = user.getId();
		resp.sessionId = session.getId();
		resp.token = session.getToken();

		return resp;
	}

	public void logout(long sessionId) throws SQLException {
		log.info("Revoking session {}", sessionId);
		sessionSecurity.revokeSession(sessionId);
	}

	private void recordAudit(Long userId, String ip, String userAgent, boolean success) throws SQLException {
		LoginAudit audit = new LoginAudit();
		audit.setUserId(userId);
		audit.setLoginTimestamp(Instant.now());
		audit.setIpAddress(ip);
		audit.setUserAgent(userAgent);
		audit.setSuccess(success);
		// dropped setRequestFingerprint to match your current LoginAudit type

		auditRepo.insert(audit);
	}
}
