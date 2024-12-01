package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.CreateUserResponse;
import com.fns.user.service.domain.dto.create.GetAllUserResponse;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Validated
@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserCreateCommandHandler userCreateCommandHandler;

    public UserApplicationServiceImpl(UserCreateCommandHandler userCreateCommandHandler) {
        this.userCreateCommandHandler = userCreateCommandHandler;
    }

    @Override
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        return userCreateCommandHandler.createUser(createUserCommand);
    }

    @Override
    public List<GetAllUserResponse> getAllUsers() {
        return userCreateCommandHandler.getAllUsers();
    }
}
