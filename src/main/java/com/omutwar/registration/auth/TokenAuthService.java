package com.omutwar.registration.auth;

import com.omutwar.registration.domain.Session;
import com.omutwar.registration.domain.User;
import com.omutwar.registration.repository.UserRepository;
import com.omutwar.registration.security.SessionSecurityService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TokenAuthService {

	private final SessionSecurityService sessionSecurity;
	private final UserRepository users;

	public TokenAuthService(SessionSecurityService sessionSecurity, UserRepository users) {
		this.sessionSecurity = sessionSecurity;
		this.users = users;
	}

	public Optional<AuthContext> authenticate(String token) {
		Optional<Session> sessionOpt = sessionSecurity.validateSession(token);
		if (sessionOpt.isEmpty())
			return Optional.empty();

		Session session = sessionOpt.get();

		Optional<User> userOpt = users.findById(session.getUserId());
		if (userOpt.isEmpty())
			return Optional.empty();

		// TODO: Replace with real user role lookup
		Set<Role> roles = RolePermissions.defaultRolesForUser();
		Set<Permission> perms = RolePermissions.permissionsForRoles(roles);

		return Optional.of(new AuthContext(session.getUserId(), roles, perms));
	}
}