package com.fns.user.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CreateUserResponse {
    @NotNull
    private final UUID userId;
    @NotNull
    private final String userName;
    @NotNull
    private final String emailAddress;
    @NotNull
    private final String role;
    @NotNull
    private final String message;
}
