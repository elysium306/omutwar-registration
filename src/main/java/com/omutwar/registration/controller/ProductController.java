package com.omutwar.registration.controller;

import com.omutwar.registration.domain.Product;
import com.omutwar.registration.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService products;

	public ProductController(ProductService products) {
		this.products = products;
	}

	@GetMapping
	public List<Product> list() {
		return products.getAll();
	}

	@GetMapping("/{id}")
	public Product get(@PathVariable Long id) {
		return products.getById(id);
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		return products.save(product);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		products.delete(id);
	}
}