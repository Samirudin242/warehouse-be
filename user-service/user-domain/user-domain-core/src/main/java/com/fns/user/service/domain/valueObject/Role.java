package com.fns.user.service.domain.valueObject;

public class Role {
    private final String roleName;

    public Role(String roleName) {
        if (roleName == null) {
            throw new IllegalArgumentException("Role name cannot be blank");
        }
        this.roleName = String.valueOf(roleName);
    }

    public String getRoleName() {
        return roleName;
    }
}