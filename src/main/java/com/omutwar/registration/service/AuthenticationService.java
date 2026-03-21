package com.omutwar.registration.service;

import com.omutwar.registration.domain.LoginAudit;
import com.omutwar.registration.domain.Session;
import com.omutwar.registration.domain.User;
import com.omutwar.registration.dto.LoginResponse;
import com.omutwar.registration.exception.NotFoundException;
import com.omutwar.registration.exception.ValidationException;
import com.omutwar.registration.logging.AppLogger;
import com.omutwar.registration.repository.LoginAuditRepository;
import com.omutwar.registration.repository.UserRepository;
import com.omutwar.registration.security.PasswordHasher;
import com.omutwar.registration.security.SessionSecurityService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthenticationService {

	private static final Logger log = AppLogger.get(AuthenticationService.class);

	private final UserRepository userRepository;
	private final LoginAuditRepository auditRepository;
	private final SessionSecurityService sessionSecurityService;

	public AuthenticationService(UserRepository userRepository, LoginAuditRepository auditRepository,
			SessionSecurityService sessionSecurityService) {
		this.userRepository = userRepository;
		this.auditRepository = auditRepository;
		this.sessionSecurityService = sessionSecurityService;
	}

	public LoginResponse login(String email, String password, String ip, String userAgent) {
		log.info("Attempting login for {}", email);

		User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Invalid credentials"));

		if (!PasswordHasher.verify(password, user.getPasswordHash())) {
			recordAudit(user.getId(), ip, userAgent, false);
			throw new ValidationException("Invalid credentials");
		}

		Session session = sessionSecurityService.createSecureSession(user.getId());

		recordAudit(user.getId(), ip, userAgent, true);

		LoginResponse resp = new LoginResponse();
		resp.userId = user.getId();
		resp.sessionId = session.getId();
		resp.token = session.getToken();

		return resp;
	}

	public void logout(long sessionId) {
		log.info("Revoking session {}", sessionId);
		sessionSecurityService.revokeSession(sessionId);
	}

	private void recordAudit(Long userId, String ip, String userAgent, boolean success) {
		LoginAudit audit = new LoginAudit();
		audit.setUserId(userId);
		audit.setLoginTimestamp(Instant.now());
		audit.setIpAddress(ip);
		audit.setUserAgent(userAgent);
		audit.setSuccess(success);

		auditRepository.save(audit);
	}
}