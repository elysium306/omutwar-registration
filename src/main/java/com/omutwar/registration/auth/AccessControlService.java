package com.omutwar.registration.auth;

import org.springframework.stereotype.Service;

@Service
public class AccessControlService {

	public boolean hasPermission(AuthContext ctx, Permission permission) {
		if (ctx == null)
			return false;
		if (ctx.permissions() == null)
			return false;

		return ctx.permissions().contains(permission);
	}

	public boolean hasRole(AuthContext ctx, Role role) {
		if (ctx == null)
			return false;
		if (ctx.roles() == null)
			return false;

		return ctx.roles().contains(role);
	}
}