package com.omutwar.registration.controller;

import java.sql.SQLException;
import java.time.Instant;

import org.slf4j.Logger;

import com.omutwar.postgres.dto.ProductDto;
import com.omutwar.postgres.mapper.ProductMapper;
import com.omutwar.registration.auth.AccessControlService;
import com.omutwar.registration.auth.AuthContext;
import com.omutwar.registration.auth.Permission;
import com.omutwar.registration.auth.TokenAuthService;
import com.omutwar.registration.domain.Product;
import com.omutwar.registration.exception.NotFoundException;
import com.omutwar.registration.logging.AppLogger;
import com.omutwar.registration.request.ProductCreateRequest;
import com.omutwar.registration.service.ProductService;
import com.omutwar.registration.validation.ValidationUtils;

public class ProductController {

	private static final Logger log = AppLogger.get(ProductController.class);
	private final ProductService service = new ProductService();
	private final AccessControlService access = new AccessControlService();
	private final TokenAuthService tokenAuthService = new TokenAuthService();

	public ProductDto getProduct(long id) throws SQLException {
		log.info("Fetching product id={}", id);

		return service.getProductById(id).map(ProductMapper::toDto)
				.orElseThrow(() -> new NotFoundException("Product not found: " + id));
	}

	public long createProduct(ProductCreateRequest req, String token) throws SQLException {
		AuthContext auth = tokenAuthService.authenticate(token)
				.orElseThrow(() -> new SecurityException("Invalid or expired token"));

		access.requirePermission(auth.getUserId(), Permission.CREATE_PRODUCT);

		ValidationUtils.requireNonEmpty(req.name, "name");
		ValidationUtils.requirePositiveNumber(req.price, "price");

		Product p = new Product();
		p.setName(req.name.trim());
		p.setDescription(req.description == null ? null : req.description.trim());
		p.setPrice(req.price);
		p.setCreatedAt(Instant.now());

		long id = service.createProduct(p);
		log.info("User {} created product {} with id={}", auth.getUserId(), req.name, id);
		return id;
	}
}