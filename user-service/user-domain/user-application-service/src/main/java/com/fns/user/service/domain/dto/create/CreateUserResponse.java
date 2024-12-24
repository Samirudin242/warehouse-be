package com.fns.user.service.domain.dto.create;

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
public class CreateUserResponse {

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

    @NotNull
    private final Boolean is_verified;
}
