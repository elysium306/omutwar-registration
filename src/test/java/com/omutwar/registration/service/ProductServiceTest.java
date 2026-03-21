package com.omutwar.registration.service;

import com.omutwar.registration.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	void testCreateProduct() {
		Product p = new Product();
		p.setName("Widget");
		p.setPrice(new BigDecimal("19.99"));

		var saved = productService.save(p);

		assertThat(saved.getId()).isNotNull();
	}
}