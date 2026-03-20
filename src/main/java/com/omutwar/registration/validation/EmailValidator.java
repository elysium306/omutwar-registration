package com.omutwar.registration.validation;

import com.omutwar.registration.exception.ValidationException;

public final class EmailValidator {

    private static final String EMAIL_REGEX =
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";

    private EmailValidator() {}

    public static void validate(String email) {
        if (email == null || !email.toUpperCase().matches(EMAIL_REGEX)) {
            throw new ValidationException("Invalid email format");
        }
    }
}