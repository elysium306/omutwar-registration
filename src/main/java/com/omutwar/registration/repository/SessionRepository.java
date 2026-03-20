package com.omutwar.registration.repository;

import com.omutwar.registration.domain.Session;
import com.omutwar.registration.util.DBUtils;

import java.sql.*;
import java.util.Optional;

public class SessionRepository extends BaseRepository {

	public long insert(Session session) throws SQLException {
		String sql = """
				    INSERT INTO sessions (user_id, token, created_at, expires_at, revoked_at, idempotency_key)
				    VALUES (?, ?, ?, ?, ?, ?)
				    RETURNING id
				""";

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);
			try {
				ps.setLong(1, session.getUserId());
				ps.setString(2, session.getToken());
				ps.setTimestamp(3, Timestamp.from(session.getCreatedAt()));
				ps.setTimestamp(4, Timestamp.from(session.getExpiresAt()));
				ps.setTimestamp(5, session.getRevokedAt() == null ? null : Timestamp.from(session.getRevokedAt()));
				ps.setString(6, session.getIdempotencyKey());

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						long id = rs.getLong(1);
						conn.commit();
						return id;
					}
					conn.rollback();
					throw new SQLException("Insert session failed");
				}
			} catch (SQLException e) {
				rollbackQuietly(conn);
				throw e;
			} finally {
				conn.setAutoCommit(true);
			}
		}
	}

	public Optional<Session> findById(long id) throws SQLException {
		String sql = """
				    SELECT id, user_id, token, created_at, expires_at, revoked_at, idempotency_key
				    FROM sessions
				    WHERE id = ?
				""";

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

	public Optional<Session> findByToken(String token) throws SQLException {
		String sql = """
				    SELECT id, user_id, token, created_at, expires_at, revoked_at, idempotency_key
				    FROM sessions
				    WHERE token = ?
				""";

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, token);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return Optional.of(mapRow(rs));
				}
				return Optional.empty();
			}
		}
	}

	public void revoke(long sessionId) throws SQLException {
		String sql = "UPDATE sessions SET revoked_at = NOW() WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, sessionId);
			ps.executeUpdate();
		}
	}

	private Session mapRow(ResultSet rs) throws SQLException {
		Session s = new Session();
		s.setId(rs.getLong("id"));
		s.setUserId(rs.getLong("user_id"));
		s.setToken(DBUtils.getString(rs, "token"));
		s.setCreatedAt(DBUtils.getInstant(rs, "created_at"));
		s.setExpiresAt(DBUtils.getInstant(rs, "expires_at"));
		s.setRevokedAt(DBUtils.getInstant(rs, "revoked_at"));
		s.setIdempotencyKey(DBUtils.getString(rs, "idempotency_key"));
		return s;
	}
}
