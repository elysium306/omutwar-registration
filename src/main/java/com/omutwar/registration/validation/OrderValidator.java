package com.omutwar.registration.validation;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.exception.ValidationException;

public final class OrderValidator {

    private OrderValidator() {}

    public static void validate(Order order) {
        if (order.getUserId() <= 0) {
            throw new ValidationException("Invalid user ID");
        }

        if (order.getTotalAmount() == null || order.getTotalAmount().doubleValue() < 0) {
            throw new ValidationException("Order amount must be positive");
        }

        if (order.getStatus() == null || order.getStatus().isBlank()) {
            throw new ValidationException("Order status cannot be empty");
        }
    }
}