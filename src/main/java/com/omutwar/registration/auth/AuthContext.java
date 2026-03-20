package com.omutwar.registration.auth;

public class AuthContext {
    private final long userId;

    public AuthContext(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}