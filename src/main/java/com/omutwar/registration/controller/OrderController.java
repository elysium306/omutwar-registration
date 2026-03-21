package com.omutwar.registration.controller;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orders;

	public OrderController(OrderService orders) {
		this.orders = orders;
	}

	@GetMapping
	public List<Order> list() {
		return orders.getAll();
	}

	@GetMapping("/{id}")
	public Order get(@PathVariable Long id) {
		return orders.getById(id);
	}

	@PostMapping
	public Order create(@RequestBody Order order) {
		return orders.save(order);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		orders.delete(id);
	}
}