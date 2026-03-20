package com.omutwar.registration.repository;

import com.omutwar.registration.domain.Product;
import com.omutwar.registration.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository extends BaseRepository {

	public Optional<Product> findById(long id) throws SQLException {
		String sql = "SELECT id, name, description, price, created_at FROM products WHERE id = ?";
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

	public long insert(Product product) throws SQLException {
		String sql = "INSERT INTO products (name, description, price, created_at) "
				+ "VALUES (?, ?, ?, ?) RETURNING id";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);
			try {
				ps.setString(1, product.getName());
				ps.setString(2, product.getDescription());
				ps.setBigDecimal(3, product.getPrice());
				ps.setTimestamp(4, Timestamp.from(product.getCreatedAt()));

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						long id = rs.getLong(1);
						conn.commit();
						return id;
					} else {
						conn.rollback();
						throw new SQLException("Insert product failed, no ID returned");
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

	public List<Product> findAll() throws SQLException {
		String sql = "SELECT id, name, description, price, created_at FROM products ORDER BY id";
		List<Product> products = new ArrayList<>();
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				products.add(mapRow(rs));
			}
		}
		return products;
	}

	private Product mapRow(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getLong("id"));
		p.setName(DBUtils.getString(rs, "name"));
		p.setDescription(DBUtils.getString(rs, "description"));
		p.setPrice(rs.getBigDecimal("price"));
		p.setCreatedAt(DBUtils.getInstant(rs, "created_at"));
		return p;
	}
}