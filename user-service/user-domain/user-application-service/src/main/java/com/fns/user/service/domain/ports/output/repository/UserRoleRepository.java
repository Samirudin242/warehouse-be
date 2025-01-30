package com.fns.user.service.domain.ports.output.repository;

import com.fns.user.service.domain.entity.Role;

import java.util.UUID;

public interface UserRoleRepository {
    Role getRoleById(UUID id);
}
