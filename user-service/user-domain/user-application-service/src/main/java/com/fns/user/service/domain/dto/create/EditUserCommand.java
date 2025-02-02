package com.fns.user.service.domain.dto.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class EditUserCommand {
    private UUID id;
    private String name;
    private String user_name;
    private String email;
    private String profile_url;
    private String phone_number;
    private UUID role_id;
}
