package com.omutwar.registration.exception;

import java.io.Serial;

public class ApiException extends RuntimeException {

    /**
     * 
     */
    @Serial
    private static final long serialVersionUID = -8336450336802764300L;
	private final int statusCode;

	public ApiException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}