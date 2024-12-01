package com.fns.user.service.domain.valueObject;

import javax.validation.constraints.NotNull;

public class UserName {
    private final String username;

    public UserName(@NotNull String username) {
        if(username == null) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        this.username = String.valueOf(username);
    }

    public String getUsername() {
        return username;
    }
}
