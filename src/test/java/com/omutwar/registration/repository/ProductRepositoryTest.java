package com.omutwar.registration.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.omutwar.registration.domain.Product;

@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {

	@Autowired
	private ProductRepository repo;

	@Test
	void testSaveProduct() {
		Product p = new Product();
		p.setName("Widget");
		p.setPrice(new BigDecimal("19.99"));

		Product saved = repo.save(p);

		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getName()).isEqualTo("Widget");
	}
}