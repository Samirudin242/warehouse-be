package com.fns.warehouse.service.domain.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class UserAuthentication extends AbstractAuthenticationToken {

    private final String username;

    public UserAuthentication(String username) {
        super(null);
        this.username = username;
        setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
