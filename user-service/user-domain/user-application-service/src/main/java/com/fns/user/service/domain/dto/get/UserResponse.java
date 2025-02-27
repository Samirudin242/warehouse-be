package com.fns.user.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@Data
public class UserResponse {

    private  final UUID id;

    @NotNull
    private final String name;

    @NotNull
    private final String email;

    @NotNull
    private final String user_name;

    private final String password;

    @NotNull
    private final String phone_number;

    @NotNull
    private final UUID role_id;

    private final String profile_picture;

    private final String province;

    private final String city;

    private final String address;

    private final String postal_code;

    private final String province_id;

    private final String city_id;

    private final String accessToken;

    @NotNull
    private final Boolean is_verified;
}
