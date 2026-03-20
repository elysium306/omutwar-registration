package com.omutwar.registration.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.util.DBUtils;

public class UserRepository extends BaseRepository {

	public Optional<User> findById(long id) throws SQLException {
		String sql = "SELECT id, email, password_hash, first_name, last_name, status, "
				+ "created_at, updated_at FROM users WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return Optional.of(mapRow(rs));
				}
				return Optional.empty();
			}
		}
	}

	public Optional<User> findByEmail(String email) throws SQLException {
		String sql = "SELECT id, email, password_hash, first_name, last_name, status, "
				+ "created_at, updated_at FROM users WHERE email = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return Optional.of(mapRow(rs));
				}
				return Optional.empty();
			}
		}
	}

	public long insert(User user) throws SQLException {
		String sql = "INSERT INTO users (email, password_hash, first_name, last_name, status, "
				+ "created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);
			try {
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPasswordHash());
				ps.setString(3, user.getFirstName());
				ps.setString(4, user.getLastName());
				ps.setString(5, user.getStatus());
				Instant now = Instant.now();
				ps.setTimestamp(6, Timestamp.from(user.getCreatedAt() != null ? user.getCreatedAt() : now));
				ps.setTimestamp(7, Timestamp.from(user.getUpdatedAt() != null ? user.getUpdatedAt() : now));

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						long id = rs.getLong(1);
						conn.commit();
						return id;
					} else {
						conn.rollback();
						throw new SQLException("Insert user failed, no ID returned");
					}
				}
			} catch (SQLException e) {
				rollbackQuietly(conn);
				throw e;
			} finally {
				conn.setAutoCommit(true);
			}
		}
	}

	public boolean update(User user) throws SQLException {
		String sql = "UPDATE users SET email = ?, password_hash = ?, first_name = ?, "
				+ "last_name = ?, status = ?, updated_at = ? WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);
			try {
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPasswordHash());
				ps.setString(3, user.getFirstName());
				ps.setString(4, user.getLastName());
				ps.setString(5, user.getStatus());
				ps.setTimestamp(6, Timestamp.from(user.getUpdatedAt() != null ? user.getUpdatedAt() : Instant.now()));
				ps.setLong(7, user.getId());

				int updated = ps.executeUpdate();
				conn.commit();
				return updated == 1;
			} catch (SQLException e) {
				rollbackQuietly(conn);
				throw e;
			} finally {
				conn.setAutoCommit(true);
			}
		}
	}

	public boolean deleteById(long id) throws SQLException {
		String sql = "DELETE FROM users WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);
			try {
				ps.setLong(1, id);
				int deleted = ps.executeUpdate();
				conn.commit();
				return deleted == 1;
			} catch (SQLException e) {
				rollbackQuietly(conn);
				throw e;
			} finally {
				conn.setAutoCommit(true);
			}
		}
	}

	public List<User> findAll(int limit, int offset) throws SQLException {
		String sql = "SELECT id, email, password_hash, first_name, last_name, status, "
				+ "created_at, updated_at FROM users ORDER BY id LIMIT ? OFFSET ?";
		List<User> users = new ArrayList<>();
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, limit);
			ps.setInt(2, offset);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					users.add(mapRow(rs));
				}
			}
		}
		return users;
	}

	private User mapRow(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getLong("id"));
		u.setEmail(DBUtils.getString(rs, "email"));
		u.setPasswordHash(DBUtils.getString(rs, "password_hash"));
		u.setFirstName(DBUtils.getString(rs, "first_name"));
		u.setLastName(DBUtils.getString(rs, "last_name"));
		u.setStatus(DBUtils.getString(rs, "status"));
		u.setCreatedAt(DBUtils.getInstant(rs, "created_at"));
		u.setUpdatedAt(DBUtils.getInstant(rs, "updated_at"));
		return u;
	}
}