package com.fns.user.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {
    private UUID id;
    private String username;
    private String email;
    private UUID role_id;
}