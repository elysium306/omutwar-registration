package com.omutwar.registration.auth;

import com.omutwar.registration.repository.UserRoleRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccessControlService {

    private final UserRoleRepository repo = new UserRoleRepository();

    public boolean hasPermission(long userId, Permission permission) throws SQLException {
        List<Role> roles = repo.getRoles(userId);

        Set<Permission> permissions = new HashSet<>();
        for (Role r : roles) {
            permissions.addAll(RolePermissions.getPermissions(r));
        }

        return permissions.contains(permission);
    }

    public void requirePermission(long userId, Permission permission) throws SQLException {
        if (!hasPermission(userId, permission)) {
            throw new SecurityException("User does not have permission: " + permission);
        }
    }
}
