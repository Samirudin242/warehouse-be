package com.fns.user.service.dataaccess.user.adapter;

import com.fns.user.service.dataaccess.user.mapper.UserRoleMapper;
import com.fns.user.service.dataaccess.user.repository.UserRoleJpaRepository;
import com.fns.user.service.domain.entity.Role;
import com.fns.user.service.domain.ports.output.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class UserRoleRepositoryImpl implements UserRoleRepository {

    private final UserRoleJpaRepository userRoleJpaRepository;
    private final UserRoleMapper userRoleMapper;
    
    public UserRoleRepositoryImpl(UserRoleJpaRepository userRoleJpaRepository, UserRoleMapper userRoleMapper) {
        this.userRoleJpaRepository = userRoleJpaRepository;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public Role getRoleById(UUID id) {
        return userRoleJpaRepository.findById(id)
                .map(userRoleMapper::roleEntityToRole)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + id));
    }
}
