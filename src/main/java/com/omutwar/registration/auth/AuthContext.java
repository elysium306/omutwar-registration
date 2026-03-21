package com.omutwar.registration.auth;

import java.util.Set;

public record AuthContext(Long userId, Set<Role> roles, Set<Permission> permissions) {
}