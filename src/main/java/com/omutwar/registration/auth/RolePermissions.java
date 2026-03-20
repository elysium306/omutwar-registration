package com.omutwar.registration.auth;

import java.util.EnumSet;
import java.util.Set;

public final class RolePermissions {

	private RolePermissions() {
	}

	public static Set<Permission> getPermissions(Role role) {
		return switch (role) {
		case USER ->
			EnumSet.of(Permission.READ_USER, Permission.READ_PRODUCT, Permission.READ_ORDER, Permission.CREATE_ORDER);
		case SUPPORT -> EnumSet.of(Permission.READ_USER, Permission.READ_PRODUCT, Permission.READ_ORDER);
		case ADMIN -> EnumSet.allOf(Permission.class);
		};
	}
}
