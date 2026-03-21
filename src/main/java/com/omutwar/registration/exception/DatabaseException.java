package com.omutwar.registration.exception;

import java.io.Serial;

public class DatabaseException extends ApiException {

    /**
     * 
     */
    @Serial
    private static final long serialVersionUID = -690658599247642893L;

	public DatabaseException(String message, Throwable cause) {
		super(message, 500);
		initCause(cause);
	}
}