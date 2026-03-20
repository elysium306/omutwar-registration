package com.omutwar.registration.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.omutwar.registration.auth.Role;

public class UserRoleRepository extends BaseRepository {

	public void assignRole(long userId, Role role) throws SQLException {
		String sql = "INSERT INTO user_roles (user_id, role) VALUES (?, ?)";

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, userId);
			ps.setString(2, role.name());
			ps.executeUpdate();
		}
	}

	public List<Role> getRoles(long userId) throws SQLException {
		String sql = "SELECT role FROM user_roles WHERE user_id = ?";

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				List<Role> roles = new ArrayList<>();
				while (rs.next()) {
					roles.add(Role.valueOf(rs.getString("role")));
				}
				return roles;
			}
		}
	}
}
