package com.omutwar.registration.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.net.URL;
import java.security.Key;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

public class JwtAuthService {

    private final JwksCache jwks = new JwksCache();

    public Optional<AuthContext> authenticate(String jwt) {
        try {
            Jws<Claims> parsed = Jwts.parserBuilder()
                    .setSigningKeyResolver(jwks)
                    .build()
                    .parseClaimsJws(jwt);

            Claims claims = parsed.getBody();

            long userId = Long.parseLong(claims.get("sub").toString());
            return Optional.of(new AuthContext(userId));

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}