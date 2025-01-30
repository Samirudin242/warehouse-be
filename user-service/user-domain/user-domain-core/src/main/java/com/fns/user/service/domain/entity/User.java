package com.fns.user.service.domain.entity;


import java.util.UUID;

import lombok.*;


@Getter
@Setter
@Data
@Builder
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
    private String province;
    private String city;
    private String address;
    private String postal_code;
    private String province_id;
    private String city_id;
    private String role_name;

}
