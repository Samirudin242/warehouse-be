package com.fns.user.service.domain.entity;

//import com.fns.user.service.domain.entity.User
import com.fns.domain.entity.AggregateRoot;
import com.fns.domain.valueobject.EmailAddress;
import com.fns.domain.valueobject.UserId;
import com.fns.user.service.domain.exception.UserDomainException;
import com.fns.user.service.domain.valueObject.Password;
import com.fns.user.service.domain.valueObject.Role;
import com.fns.user.service.domain.valueObject.UserName;

import java.util.UUID;

public class User extends AggregateRoot<UserId> {


    private final UUID id;
    private final String username;
    private final String password;
    private final String role;
    private final String email;
    private boolean isActive;

    public User( UUID id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.isActive = true;
    }

    private User(Builder builder) {
//        super.setId(builder.userId);
        this.id = builder.id;
        this.username = builder.userName;
        this.password = builder.password;
        this.email = builder.email;
        this.role = builder.role;
    }

    public static Builder builder() {
        return new Builder();
    }

    //Getters
    public String getUsername() {
        return username;
    }
    public UUID getIdUser() { return  id;}
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public String getEmail() {
        return email;
    }
    public boolean isActive() {
        return isActive;
    }
    public void deactivate() {
        this.isActive = false;
    }

    //Builder class
    public static class Builder {
        private UUID id;
        private String userName;
        private String password;
        private String email;
        private String role;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            if (userName == null) {
             throw new UserDomainException("User name cannot be empty");
            }
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            if (userName == null) {
                throw new UserDomainException("Password cannot be empty");
            }
            this.password = password;
            return this;
        }

        public Builder emailAddress(String emailAddress) {
            if (userName == null) {
                throw new UserDomainException("Email cannot be empty");
            }
            this.email = emailAddress;
            return this;
        }

        public Builder role (String role) {
            if (role == null) {
                throw new UserDomainException("Email cannot be empty");
            }
            this.role = role;
            return this;
        }

        public User build() {
            if(this.userName == null) {
                throw new UserDomainException("Username must be set");
            }
            if(this.email == null) {
                throw new UserDomainException("Email must be set");
            }
            if(this.password == null) {
                throw new UserDomainException("Password must be set");
            }
            if(this.role == null) {
                throw new UserDomainException("Role must be set");
            }

            return new User(this);
        }
    }
}
