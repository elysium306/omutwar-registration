package com.omutwar.registration.util;

public final class QueryUtils {

	private QueryUtils() {
	}

	public static String limitOffset(int limit, int offset) {
		return " LIMIT " + limit + " OFFSET " + offset + " ";
	}

	public static String orderBy(String column, boolean asc) {
		return " ORDER BY " + column + (asc ? " ASC " : " DESC ");
	}

	public static String like(String column) {
		return column + " ILIKE ? ";
	}

	public static String eq(String column) {
		return column + " = ? ";
	}

	public static String and() {
		return " AND ";
	}

	public static String or() {
		return " OR ";
	}
}