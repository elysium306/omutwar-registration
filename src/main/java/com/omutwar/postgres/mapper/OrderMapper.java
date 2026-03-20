package com.omutwar.postgres.mapper;

import com.omutwar.postgres.dto.OrderDto;
import com.omutwar.registration.domain.Order;

public final class OrderMapper {

	private OrderMapper() {
	}

	public static OrderDto toDto(Order o) {
		OrderDto dto = new OrderDto();
		dto.id = o.getId();
		dto.userId = o.getUserId();
		dto.totalAmount = o.getTotalAmount();
		dto.status = o.getStatus();
		return dto;
	}
}