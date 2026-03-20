package com.omutwar.registration.security;

import java.security.SecureRandom;
import java.util.Base64;

public final class TokenGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    private TokenGenerator() {}

    public static String generateToken(int bytes) {
        byte[] buffer = new byte[bytes];
        RANDOM.nextBytes(buffer);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(buffer);
    }

    public static String sessionToken() {
        return generateToken(32); // 256-bit token
    }

    public static String emailVerificationToken() {
        return generateToken(24);
    }

    public static String passwordResetToken() {
        return generateToken(24);
    }
}
