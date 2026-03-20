package com.omutwar.registration.repository;

import com.omutwar.registration.domain.LoginAudit;
import com.omutwar.registration.util.DBUtils;

import java.sql.*;
import java.util.Optional;

public class LoginAuditRepository extends BaseRepository {

    public long insert(LoginAudit audit) throws SQLException {
        String sql = """
            INSERT INTO login_audit (user_id, login_timestamp, ip_address, user_agent, success_flag)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);
            try {
                ps.setLong(1, audit.getUserId());
                ps.setTimestamp(2, Timestamp.from(audit.getLoginTimestamp()));
                ps.setString(3, audit.getIpAddress());
                ps.setString(4, audit.getUserAgent());
                ps.setBoolean(5, audit.isSuccess());

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        conn.commit();
                        return id;
                    }
                    conn.rollback();
                    throw new SQLException("Insert login audit failed");
                }
            } catch (SQLException e) {
                rollbackQuietly(conn);
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    public Optional<LoginAudit> findById(long id) throws SQLException {
        String sql = """
            SELECT id, user_id, login_timestamp, ip_address, user_agent, success_flag
            FROM login_audit
            WHERE id = ?
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        }
    }

    private LoginAudit mapRow(ResultSet rs) throws SQLException {
        LoginAudit a = new LoginAudit();
        a.setId(rs.getLong("id"));
        a.setUserId(DBUtils.getLong(rs, "user_id"));
        a.setLoginTimestamp(DBUtils.getInstant(rs, "login_timestamp"));
        a.setIpAddress(DBUtils.getString(rs, "ip_address"));
        a.setUserAgent(DBUtils.getString(rs, "user_agent"));
        a.setSuccess(rs.getBoolean("success_flag"));
        return a;
    }
}