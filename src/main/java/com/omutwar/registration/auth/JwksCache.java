package com.omutwar.registration.auth;

import java.math.BigInteger;
import java.net.URL;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;

public class JwksCache extends SigningKeyResolverAdapter {

	private final Map<String, PublicKey> keys = new ConcurrentHashMap<>();
	private final ObjectMapper mapper = new ObjectMapper();

	private static final String JWKS_URL = "https://cognito-idp.<region>.amazonaws.com/<userPoolId>/.well-known/jwks.json";

	@SuppressWarnings("rawtypes")
	@Override
	public Key resolveSigningKey(JwsHeader header, Claims claims) {
		return keys.computeIfAbsent(header.getKeyId(), this::fetchKey);
	}

	private PublicKey fetchKey(String kid) {
		try {
			JsonNode jwks = mapper.readTree(new URL(JWKS_URL));

			for (JsonNode key : jwks.get("keys")) {
				if (key.get("kid").asText().equals(kid)) {

					String modulusB64 = key.get("n").asText();
					String exponentB64 = key.get("e").asText();

					byte[] modulusBytes = Base64.getUrlDecoder().decode(modulusB64);
					byte[] exponentBytes = Base64.getUrlDecoder().decode(exponentB64);

					BigInteger modulus = new BigInteger(1, modulusBytes);
					BigInteger exponent = new BigInteger(1, exponentBytes);

					RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exponent);
					KeyFactory factory = KeyFactory.getInstance("RSA");

					return factory.generatePublic(spec);
				}
			}
		} catch (Exception ignored) {
		}

		return null;
	}
}