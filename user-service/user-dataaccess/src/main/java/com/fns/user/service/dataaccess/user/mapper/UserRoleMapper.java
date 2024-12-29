package com.fns.user.service.dataaccess.user.mapper;

import com.fns.user.service.dataaccess.user.entity.UserRoleEntity;
import com.fns.user.service.domain.dto.get.RoleResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRoleMapper {

    public RoleResponse userRoleEntityToResponse(UserRoleEntity userRoleEntity) {
        return RoleResponse.builder()
                .id(userRoleEntity.getId())
                .role_name(userRoleEntity.getRole_name())
                .build();
    }

    public List<RoleResponse> userRoleEntitiesToResponse(List<UserRoleEntity> userRole) {
            return userRole.stream()
                    .map(this::userRoleEntityToResponse)
                    .collect(Collectors.toList());
    }
}
