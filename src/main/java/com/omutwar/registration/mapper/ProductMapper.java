package com.omutwar.registration.mapper;

import com.omutwar.registration.domain.Product;
import com.omutwar.registration.dto.ProductDto;

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