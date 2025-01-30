package com.fns.user.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
@Data
public class LoginUserCommand {

    @NotNull
    private final String usernameEmail;

    @NotNull
    private final String password;

}
