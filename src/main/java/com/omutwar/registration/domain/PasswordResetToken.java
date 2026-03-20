package com.omutwar.registration.domain;

import java.time.Instant;

public class PasswordResetToken {

	private long id;
	private long userId;
	private String token;
	private Instant createdAt;
	private Instant expiresAt;
	private Instant usedAt;

	public PasswordResetToken() {
	}

	public PasswordResetToken(long id, long userId, String token, Instant createdAt, Instant expiresAt,
			Instant usedAt) {
		this.id = id;
		this.userId = userId;
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.usedAt = usedAt;
	}

	// Getters and setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Instant getUsedAt() {
		return usedAt;
	}

	public void setUsedAt(Instant usedAt) {
		this.usedAt = usedAt;
	}
}