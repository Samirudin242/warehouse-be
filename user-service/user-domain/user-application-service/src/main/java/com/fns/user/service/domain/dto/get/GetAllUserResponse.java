package com.fns.user.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String role_id;
    private String role_name;
    private String address;
    private String city;
    private String province;
    private String profile_picture;
}