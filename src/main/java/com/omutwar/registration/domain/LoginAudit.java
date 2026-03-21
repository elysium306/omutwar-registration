package com.omutwar.registration.domain;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login_audit")
public class LoginAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId; // nullable for unknown user
	private Instant loginTimestamp;
	private String ipAddress;
	private String userAgent;
	private boolean success;
	private String requestFingerprint;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Instant getLoginTimestamp() {
		return loginTimestamp;
	}

	public void setLoginTimestamp(Instant loginTimestamp) {
		this.loginTimestamp = loginTimestamp;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getRequestFingerprint() {
		return requestFingerprint;
	}

	public void setRequestFingerprint(String requestFingerprint) {
		this.requestFingerprint = requestFingerprint;
	}
}
