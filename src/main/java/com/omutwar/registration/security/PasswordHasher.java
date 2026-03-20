package com.omutwar.registration.security;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordHasher {

	private static final int WORK_FACTOR = 12;

	private PasswordHasher() {
	}

	public static String hash(String plain) {
		return BCrypt.hashpw(plain, BCrypt.gensalt(WORK_FACTOR));
	}

	public static boolean verify(String plain, String hashed) {
		return BCrypt.checkpw(plain, hashed);
	}
}