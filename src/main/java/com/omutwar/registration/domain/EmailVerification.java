package com.omutwar.registration.domain;

import java.time.Instant;

public class EmailVerification {

	private long id;
	private long userId;
	private String token;
	private Instant createdAt;
	private Instant verifiedAt;

	public EmailVerification() {
	}

	public EmailVerification(long id, long userId, String token, Instant createdAt, Instant verifiedAt) {
		this.id = id;
		this.userId = userId;
		this.token = token;
		this.createdAt = createdAt;
		this.verifiedAt = verifiedAt;
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

	public Instant getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Instant verifiedAt) {
		this.verifiedAt = verifiedAt;
	}
}