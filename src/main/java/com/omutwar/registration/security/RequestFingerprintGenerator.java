package com.omutwar.registration.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class RequestFingerprintGenerator {

    private RequestFingerprintGenerator() {}

    public static String fingerprint(String ip, String userAgent) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String combined = (ip == null ? "" : ip) + "|" + (userAgent == null ? "" : userAgent);
            byte[] hash = digest.digest(combined.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
