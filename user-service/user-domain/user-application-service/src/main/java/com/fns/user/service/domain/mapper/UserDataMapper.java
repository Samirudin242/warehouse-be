package com.fns.user.service.domain.mapper;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.CreateUserResponse;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.valueObject.UserName;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

    public CreateUserResponse userToCreateUserResponse(CreateUserCommand createUserCommand) {
        return CreateUserResponse.builder()
                .userName(createUserCommand.getUsername())
                .emailAddress(createUserCommand.getEmail())
                .role(createUserCommand.getRole())
                .build();

    }

    public User createUser(CreateUserCommand createUserCommand) {
        return User.builder()
                .userName(createUserCommand.getUsername().toString())
                .emailAddress(createUserCommand.getEmail().toString())
                .role(createUserCommand.getRole().toString())
                .password(createUserCommand.getPassword().toString())
                .build();
    }

}
