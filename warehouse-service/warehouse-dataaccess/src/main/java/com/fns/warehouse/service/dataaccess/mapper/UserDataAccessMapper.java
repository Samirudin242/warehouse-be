package com.fns.warehouse.service.dataaccess.mapper;

import com.fns.warehouse.service.dataaccess.entity.UserEntity;
import com.fns.warehouse.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessMapper {
    public UserEntity userToUserEntity(User user) {
        // Map the User to UserEntity
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .user_name(user.getUser_name())
                .email(user.getEmail())
                .phone_number(user.getPhone_number())
                .is_verified(user.getIs_verified())
                .profile_picture(user.getProfile_picture())
                .build();
    }

    public User userEntityToUser(UserEntity userEntity) {
        // Map the UserEntity to User
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .user_name(userEntity.getUser_name())
                .email(userEntity.getEmail())
                .phone_number(userEntity.getPhone_number())
                .is_verified(userEntity.getIs_verified())
                .profile_picture(userEntity.getProfile_picture())
                .build();
    }
}
