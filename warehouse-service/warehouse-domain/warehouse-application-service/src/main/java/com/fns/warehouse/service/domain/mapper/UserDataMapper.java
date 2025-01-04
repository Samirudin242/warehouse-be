package com.fns.warehouse.service.domain.mapper;

import com.fns.warehouse.service.domain.dto.message.UserModel;
import com.fns.warehouse.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

    public User userModelToUser(UserModel userModel) {
        return User.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .user_name(userModel.getUserName())
                .email(userModel.getEmail())
                .phone_number(userModel.getPhoneNumber())
                .profile_picture(userModel.getProfilePicture())
                .is_verified(userModel.getIsVerified())
                .build();
    }

}
