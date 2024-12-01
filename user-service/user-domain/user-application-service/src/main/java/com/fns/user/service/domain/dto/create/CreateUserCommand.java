package com.fns.user.service.domain.dto.create;

import com.fns.domain.valueobject.EmailAddress;
import com.fns.user.service.domain.valueObject.Password;
import com.fns.user.service.domain.valueObject.Role;
import com.fns.user.service.domain.valueObject.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@Builder
public class CreateUserCommand {
    @NotNull
    private final String username;
    @NotNull
    private final String email;
    @NotNull
    private final String password;
    @NotNull
    private final String role;

}
