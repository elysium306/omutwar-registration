package com.omutwar.registration.auth;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RolePermissions {

	private static final Map<Role, Set<Permission>> ROLE_MAP = Map.of(Role.ADMIN,
			Set.of(Permission.READ, Permission.WRITE, Permission.DELETE), Role.USER, Set.of(Permission.READ));

	public static Set<Role> defaultRolesForUser() {
		// TODO: Replace with real logic (e.g., user.getRole())
		return Set.of(Role.USER);
	}

	public static Set<Permission> permissionsForRoles(Set<Role> roles) {
		return roles.stream().flatMap(r -> ROLE_MAP.getOrDefault(r, Set.of()).stream()).collect(Collectors.toSet());
	}
}