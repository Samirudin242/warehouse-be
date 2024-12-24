package com.fns.user.service.domain.entity;

import com.fns.domain.entity.AggregateRoot;
import com.fns.domain.valueobject.UserId;
import com.fns.user.service.domain.exception.UserDomainException;


import java.util.UUID;

import lombok.*;


@Getter
@Setter
@Data
public class User{

    private UUID id;
    private String name;
    private String user_name;
    private String password;
    private UUID role_id;
    private String email;
    private String phone_number;
    private Boolean is_verified;
    private String profile_picture;


    @Builder
    public User(UUID id, String name, String userName, String password, String email, UUID role_id, Boolean is_verified, String phone_number, String profile_picture) {
        if (userName == null) throw new UserDomainException("Username cannot be empty");
        if (email == null) throw new UserDomainException("Email cannot be empty");
        if (role_id == null) throw new UserDomainException("Role cannot be empty");

        this.id = id;
        this.name = name;
        this.user_name = userName;
        this.password = password;
        this.email = email;
        this.role_id = role_id;
        this.is_verified = is_verified;
        this.profile_picture = profile_picture;
        this.phone_number = phone_number;
    }

}
