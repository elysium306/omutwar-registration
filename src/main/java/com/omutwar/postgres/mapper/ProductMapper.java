package com.omutwar.postgres.mapper;

import com.omutwar.postgres.dto.ProductDto;
import com.omutwar.registration.domain.Product;

public final class ProductMapper {

	private ProductMapper() {
	}

	public static ProductDto toDto(Product p) {
		ProductDto dto = new ProductDto();
		dto.id = p.getId();
		dto.name = p.getName();
		dto.description = p.getDescription();
		dto.price = p.getPrice();
		return dto;
	}
}