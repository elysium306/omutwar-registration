package com.omutwar.registration.service;

import com.omutwar.registration.domain.Product;
import com.omutwar.registration.repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductService {

	private final ProductRepository productRepository = new ProductRepository();

	public long createProduct(Product product) throws SQLException {
		return productRepository.insert(product);
	}

	public Optional<Product> getProductById(long id) throws SQLException {
		return productRepository.findById(id);
	}

	public List<Product> listProducts() throws SQLException {
		return productRepository.findAll();
	}
}