package com.omutwar.registration.repository;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository extends BaseRepository {

	public Optional<Order> findById(long id) throws SQLException {
		String sql = "SELECT id, user_id, total_amount, status, created_at " + "FROM orders WHERE id = ?";
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

	public long insert(Order order) throws SQLException {
		String sql = "INSERT INTO orders (user_id, total_amount, status, created_at) "
				+ "VALUES (?, ?, ?, ?) RETURNING id";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);
			try {
				ps.setLong(1, order.getUserId());
				ps.setBigDecimal(2, order.getTotalAmount());
				ps.setString(3, order.getStatus());
				ps.setTimestamp(4, Timestamp.from(order.getCreatedAt()));

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						long id = rs.getLong(1);
						conn.commit();
						return id;
					} else {
						conn.rollback();
						throw new SQLException("Insert order failed, no ID returned");
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

	public List<Order> findByUserId(long userId) throws SQLException {
		String sql = "SELECT id, user_id, total_amount, status, created_at "
				+ "FROM orders WHERE user_id = ? ORDER BY created_at DESC";
		List<Order> orders = new ArrayList<>();
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					orders.add(mapRow(rs));
				}
			}
		}
		return orders;
	}

	private Order mapRow(ResultSet rs) throws SQLException {
		Order o = new Order();
		o.setId(rs.getLong("id"));
		o.setUserId(rs.getLong("user_id"));
		o.setTotalAmount(rs.getBigDecimal("total_amount"));
		o.setStatus(DBUtils.getString(rs, "status"));
		o.setCreatedAt(DBUtils.getInstant(rs, "created_at"));
		return o;
	}
}