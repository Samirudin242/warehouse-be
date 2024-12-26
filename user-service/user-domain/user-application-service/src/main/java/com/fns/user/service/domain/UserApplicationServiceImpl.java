package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.LoginUserCommand;
import com.fns.user.service.domain.dto.get.LoginResponse;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.dto.get.GetAllUserResponse;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserCommandHandler userCommandHandler;

    public UserApplicationServiceImpl(UserCommandHandler userCommandHandler) {
        this.userCommandHandler = userCommandHandler;
    }

    @Override
    public UserResponse createUser(CreateUserCommand createUserCommand) {
        return userCommandHandler.createUser(createUserCommand);
    }

    @Override
    public UserResponse editUser(UUID id, CreateUserCommand createUserCommand) {
        return userCommandHandler.editUser(id, createUserCommand);
    }

    @Override
    public List<GetAllUserResponse> getAllUsers() {
        return userCommandHandler.getAllUsers();
    }

    @Override
    public UserResponse getUserById(UUID id) {
        return userCommandHandler.getUserById(id);
    }

    @Override
    public LoginResponse login(LoginUserCommand loginCommand) {
        return userCommandHandler.login(loginCommand);
    }
}
