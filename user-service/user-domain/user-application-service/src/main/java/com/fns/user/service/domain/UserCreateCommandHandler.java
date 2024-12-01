package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.CreateUserResponse;
import com.fns.user.service.domain.dto.create.GetAllUserResponse;
import com.fns.user.service.domain.mapper.UserDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserCreateCommandHandler {

    private final UserCreateHelper userCreateHelper;

    private final UserDataMapper userDataMapper;

    private final UserGetAllHandler userGetAllHandler;

    public UserCreateCommandHandler(UserCreateHelper userCreateHelper, UserDataMapper userDataMapper, UserGetAllHandler userGetAllHandler) {
        this.userCreateHelper = userCreateHelper;
        this.userDataMapper = userDataMapper;
        this.userGetAllHandler = userGetAllHandler;
    }

    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        /*
            Event here if any
         */
        CreateUserResponse createUser = userCreateHelper.userCreateMethod(createUserCommand);
        return userDataMapper.userToCreateUserResponse(createUserCommand);
    }

    public List<GetAllUserResponse> getAllUsers() {
        return userGetAllHandler.getAllUser();
    }

}
