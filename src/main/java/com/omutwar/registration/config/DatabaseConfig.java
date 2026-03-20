package com.omutwar.registration.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

	private static final Properties PROPS = new Properties();

	static {
		try (InputStream in = DatabaseConfig.class.getClassLoader().getResourceAsStream("application.properties")) {

			if (in == null) {
				throw new IllegalStateException("application.properties not found");
			}

			PROPS.load(in);

		} catch (IOException e) {
			throw new RuntimeException("Failed to load DB config", e);
		}
	}

	public static String get(String key) {
		return PROPS.getProperty(key);
	}

	public static int getInt(String key, int defaultValue) {
		String value = PROPS.getProperty(key);
		return value == null ? defaultValue : Integer.parseInt(value);
	}
}