package com.omutwar.registration.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class Product {

	private long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Instant createdAt;

	public Product() {
	}

	public Product(long id, String name, String description, BigDecimal price, Instant createdAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
}