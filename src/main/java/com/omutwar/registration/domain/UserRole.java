package com.omutwar.registration.domain;

import com.omutwar.registration.auth.Role;

public class UserRole {
	private long userId;
	private Role role;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
