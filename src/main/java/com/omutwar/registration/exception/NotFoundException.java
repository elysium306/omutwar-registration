package com.omutwar.registration.exception;

public class NotFoundException extends ApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7352264693517756764L;

	public NotFoundException(String message) {
		super(message, 404);
	}
}