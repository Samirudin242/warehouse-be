package com.fns.user.service.domain.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class UserAuthentication extends AbstractAuthenticationToken {

    private final String username;

    public UserAuthentication(String username) {
        super(null); // No authorities provided
        this.username = username;
        setAuthenticated(true); // Mark as authenticated
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return null; // No credentials required here
    }
}
