package com.omutwar.registration.service;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

	private final OrderRepository orders;

	public OrderService(OrderRepository orders) {
		this.orders = orders;
	}

	public List<Order> getAll() {
		return orders.findAll();
	}

	public Order getById(Long id) {
		return orders.findById(id).orElseThrow();
	}

	public Order save(Order order) {
		return orders.save(order);
	}

	public void delete(Long id) {
		orders.deleteById(id);
	}
}