package com.omutwar.registration.service;

import com.omutwar.registration.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private final ProductService service = new ProductService();

    @Test
    void testCreateProduct() throws SQLException {
        Product p = new Product();
        p.setName("Phone");
        p.setDescription("Smartphone");
        p.setPrice(new BigDecimal("799.99"));
        p.setCreatedAt(Instant.now());

        long id = service.createProduct(p);
        assertTrue(service.getProductById(id).isPresent());
    }
}