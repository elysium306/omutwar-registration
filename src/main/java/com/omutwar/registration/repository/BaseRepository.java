//package com.omutwar.registration.repository;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import com.omutwar.registration.config.DataSourceProvider;
//
//public abstract class BaseRepository {
//
//	protected DataSource dataSource() {
//		return DataSourceProvider.get();
//	}
//
//	protected Connection getConnection() throws SQLException {
//		return dataSource().getConnection();
//	}
//
//	protected void rollbackQuietly(Connection conn) {
//		if (conn == null)
//			return;
//		try {
//			conn.rollback();
//		} catch (Exception ignored) {
//		}
//	}
//}