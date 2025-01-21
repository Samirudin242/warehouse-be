package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.UserEntity;
import com.fns.product.service.domain.dto.message.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {
    public UserEntity userFromUserModel(UserModel userModel) {
        return UserEntity.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .user_name(userModel.getUserName())
                .email(userModel.getEmail())
                .profile_picture(userModel.getProfilePicture())
                .phone_number(userModel.getPhoneNumber())
                .is_verified(true)
                .build();
    }
}
