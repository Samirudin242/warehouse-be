package com.fns.user.service.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@Builder
public class Role {
    private UUID id;
    private String role_name;
}
