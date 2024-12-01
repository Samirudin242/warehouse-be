package com.fns.user.service.domain.valueObject;

import javax.validation.constraints.NotNull;

public class Password {
    private final String hashedPassword;

    // Modify constructor to accept a String
    public Password(@NotNull String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public static Password fromPlainText(String plainTextPassword) {
        if (plainTextPassword.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        String hashed = Integer.toString(plainTextPassword.hashCode());
        return new Password(hashed); // Now this line works correctly
    }

    public boolean verify(String plainTextPassword) {
        return hashedPassword.equals(Integer.toString(plainTextPassword.hashCode()));
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
