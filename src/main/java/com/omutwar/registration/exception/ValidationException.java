package com.omutwar.registration.exception;

import java.io.Serial;

public class ValidationException extends ApiException {

    /**
     * 
     */
    @Serial
    private static final long serialVersionUID = 8786044444384368336L;

	public ValidationException(String message) {
        super(message, 400);
    }
}