package com.fns.user.service.domain.dto.create;

import com.fns.domain.valueobject.EmailAddress;
import com.fns.user.service.domain.valueObject.Password;
import com.fns.user.service.domain.valueObject.Role;
import com.fns.user.service.domain.valueObject.UserName;
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
public class CreateUserCommand {

    private final String name;

    @NotNull
    private final String user_name;

    @NotNull
    private final String email;

    private final String password;

    private final String phone_number;

    private final String profile_picture;

    private final Boolean is_verified;

    private final String province;

    private final String city;

    private final String address;

    private final String postal_code;

    private final String province_id;

    private final String city_id;

    @NotNull
    private final UUID role_id;

}
