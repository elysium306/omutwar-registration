package com.omutwar.registration.auth;

import java.net.URL;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.SigningKeyResolverAdapter;

public class JwksCache extends SigningKeyResolverAdapter {

    private final Map<String, PublicKey> keys = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    private static final String JWKS_URL =
            "https://cognito-idp.<region>.amazonaws.com/<userPoolId>/.well-known/jwks.json";

    @Override
    public PublicKey resolveSigningKey(JwsHeader header, Claims claims) {
        return keys.computeIfAbsent(header.getKeyId(), this::fetchKey);
    }

    private PublicKey fetchKey(String kid) {
        try {
            JsonNode jwks = mapper.readTree(new URL(JWKS_URL));
            for (JsonNode key : jwks.get("keys")) {
                if (key.get("kid").asText().equals(kid)) {
                    String modulus = key.get("n").asText();
                    String exponent = key.get("e").asText();

                    byte[] decoded = Base64.getUrlDecoder().decode(modulus);
                    X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
                    return KeyFactory.getInstance("RSA").generatePublic(spec);
                }
            }
        } catch (Exception ignored) {}

        return null;
    }
}