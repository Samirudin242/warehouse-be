package com.fns.user.service.dataaccess.user.mapper;

import com.fns.user.service.dataaccess.user.entity.UserEntity;
import com.fns.user.service.dataaccess.user.entity.UserRoleEntity;
import com.fns.user.service.dataaccess.user.repository.UserRoleJpaRepository;
import com.fns.user.service.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserDataAccessMapper {

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    public UserEntity userToUserEntity(User user) {

        UserRoleEntity userRole = getRoleEntityById(user.getRole_id());

        return UserEntity.builder()
                .name(user.getName())
                .user_name(user.getUser_name())
                .password(user.getPassword())
                .email(user.getEmail())
                .user_role(userRole)
                .is_verified(user.getIs_verified())
                .profile_picture(user.getProfile_picture())
                .phone_number(user.getPhone_number())
                .build();
    }

    public User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .userName(userEntity.getUser_name())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .phone_number(userEntity.getPhone_number())
                .is_verified(userEntity.getIs_verified())
                .role_id(userEntity.getUser_role().getId())
                .profile_picture(userEntity.getProfile_picture())
                .build();
    }

    public List<User> userEntitiesToUsers(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(this::userEntityToUser)
                .collect(Collectors.toList());
    }

    private UserRoleEntity getRoleEntityById(UUID roleId) {
        return userRoleJpaRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + roleId));
    }

}
