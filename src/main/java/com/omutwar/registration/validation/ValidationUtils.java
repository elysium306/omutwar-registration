package com.omutwar.registration.validation;

import com.omutwar.registration.exception.ValidationException;

public final class ValidationUtils {

    private ValidationUtils() {}

    public static void requireNonEmpty(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(fieldName + " cannot be empty");
        }
    }

    public static void requirePositiveNumber(Number value, String fieldName) {
        if (value == null || value.doubleValue() < 0) {
            throw new ValidationException(fieldName + " must be positive");
        }
    }

    public static void requireMinLength(String value, int min, String fieldName) {
        if (value == null || value.length() < min) {
            throw new ValidationException(fieldName + " must be at least " + min + " characters");
        }
    }
}