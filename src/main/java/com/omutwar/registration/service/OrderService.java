package com.omutwar.registration.service;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.repository.OrderRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderService {

	private final OrderRepository orderRepository = new OrderRepository();

	public long createOrder(Order order) throws SQLException {
		return orderRepository.insert(order);
	}

	public Optional<Order> getOrderById(long id) throws SQLException {
		return orderRepository.findById(id);
	}

	public List<Order> getOrdersForUser(long userId) throws SQLException {
		return orderRepository.findByUserId(userId);
	}
}