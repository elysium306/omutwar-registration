package com.omutwar.registration.exception;

public class DatabaseException extends ApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -690658599247642893L;

	public DatabaseException(String message, Throwable cause) {
		super(message, 500);
		initCause(cause);
	}
}