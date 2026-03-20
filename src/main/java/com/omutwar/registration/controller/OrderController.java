package com.omutwar.registration.controller;

import java.sql.SQLException;
import java.time.Instant;

import org.slf4j.Logger;

import com.omutwar.postgres.dto.OrderDto;
import com.omutwar.postgres.mapper.OrderMapper;
import com.omutwar.registration.domain.Order;
import com.omutwar.registration.exception.NotFoundException;
import com.omutwar.registration.logging.AppLogger;
import com.omutwar.registration.request.OrderCreateRequest;
import com.omutwar.registration.service.OrderService;
import com.omutwar.registration.validation.OrderValidator;
import com.omutwar.registration.validation.ValidationUtils;

public class OrderController {

    private static final Logger log = AppLogger.get(OrderController.class);
    private final OrderService service = new OrderService();

    public OrderDto getOrder(long id) throws SQLException {
        log.info("Fetching order id={}", id);

        return service.getOrderById(id)
                .map(OrderMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Order not found: " + id));
    }

    public long createOrder(OrderCreateRequest req) throws SQLException {
        log.info("Creating order for userId={}", req.userId);

        // Validate
        ValidationUtils.requirePositiveNumber(req.userId, "userId");
        ValidationUtils.requirePositiveNumber(req.totalAmount, "totalAmount");
        ValidationUtils.requireNonEmpty(req.status, "status");

        // Map request → domain
        Order o = new Order();
        o.setUserId(req.userId);
        o.setTotalAmount(req.totalAmount);
        o.setStatus(req.status.trim());
        o.setCreatedAt(Instant.now());

        // Domain-level validation
        OrderValidator.validate(o);

        long id = service.createOrder(o);
        log.info("Order created successfully with id={}", id);

        return id;
    }
}