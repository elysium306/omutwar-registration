package com.omutwar.registration.auth;

import java.util.Set;

public interface AuthPrincipal {
    long getUserId();
    Set<String> getRoles();
}