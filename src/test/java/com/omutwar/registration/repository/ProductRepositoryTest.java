package com.omutwar.registration.repository;

import com.omutwar.registration.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    private final ProductRepository repo = new ProductRepository();

    @Test
    void testInsertAndFind() throws SQLException {
        Product p = new Product();
        p.setName("Laptop");
        p.setDescription("High-end gaming laptop");
        p.setPrice(new BigDecimal("1999.99"));
        p.setCreatedAt(Instant.now());

        long id = repo.insert(p);
        assertTrue(repo.findById(id).isPresent());
    }

    @Test
    void testFindAll() throws SQLException {
        List<Product> products = repo.findAll();
        assertFalse(products.isEmpty());
    }
}