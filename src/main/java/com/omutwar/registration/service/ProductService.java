package com.omutwar.registration.service;

import com.omutwar.registration.domain.Product;
import com.omutwar.registration.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	private final ProductRepository products;

	public ProductService(ProductRepository products) {
		this.products = products;
	}

	public List<Product> getAll() {
		return products.findAll();
	}

	public Product getById(Long id) {
		return products.findById(id).orElseThrow();
	}

	public Product save(Product product) {
		return products.save(product);
	}

	public void delete(Long id) {
		products.deleteById(id);
	}
}