package com.omutwar.registration.exception;

import java.io.Serial;

public class NotFoundException extends ApiException {

    /**
     * 
     */
    @Serial
    private static final long serialVersionUID = 7352264693517756764L;

	public NotFoundException(String message) {
		super(message, 404);
	}
}