package com.omutwar.registration.exception;

public class ValidationException extends ApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8786044444384368336L;

	public ValidationException(String message) {
        super(message, 400);
    }
}