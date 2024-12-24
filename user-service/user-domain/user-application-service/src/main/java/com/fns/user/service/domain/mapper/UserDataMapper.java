package com.fns.user.service.domain.mapper;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.CreateUserResponse;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.valueObject.UserName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDataMapper {

    public CreateUserResponse userToCreateUserResponse(CreateUserCommand createUserCommand) {
        return CreateUserResponse.builder()
                .name(createUserCommand.getName())
                .user_name(createUserCommand.getUser_name())
                .email(createUserCommand.getEmail())
                .profile_picture(createUserCommand.getProfile_picture())
                .phone_number(createUserCommand.getPhone_number())
                .is_verified(createUserCommand.getIs_verified())
                .role_id(createUserCommand.getRole_id())
                .build();

    }

    public User createUser(CreateUserResponse createUserResponse) {
        return User.builder()
                .name(createUserResponse.getName())
                .userName(createUserResponse.getUser_name())
                .email(createUserResponse.getEmail())
                .profile_picture(createUserResponse.getProfile_picture())
                .role_id(createUserResponse.getRole_id())
                .password(createUserResponse.getPassword())
                .phone_number(createUserResponse.getPhone_number())
                .is_verified(createUserResponse.getIs_verified())
                .build();
    }

}
