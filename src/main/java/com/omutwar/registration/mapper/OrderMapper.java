package com.omutwar.registration.mapper;

import com.omutwar.registration.domain.Order;
import com.omutwar.registration.dto.OrderDto;

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