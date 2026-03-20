package com.omutwar.registration.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public final class DBUtils {

	private DBUtils() {
	}

	public static Long getLong(ResultSet rs, String column) throws SQLException {
		long v = rs.getLong(column);
		return rs.wasNull() ? null : v;
	}

	public static String getString(ResultSet rs, String column) throws SQLException {
		return rs.getString(column);
	}

	public static Boolean getBoolean(ResultSet rs, String column) throws SQLException {
		boolean v = rs.getBoolean(column);
		return rs.wasNull() ? null : v;
	}

	public static Instant getInstant(ResultSet rs, String column) throws SQLException {
		java.sql.Timestamp ts = rs.getTimestamp(column);
		return ts == null ? null : ts.toInstant();
	}
}