package com.fns.user.service.domain.ports.input.service;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.LoginUserCommand;
import com.fns.user.service.domain.dto.get.LoginResponse;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.dto.get.GetAllUserResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface UserApplicationService {
    UserResponse createUser(@Valid CreateUserCommand createUserCommand);

    UserResponse editUser(UUID id, CreateUserCommand createUserCommand);

    List<GetAllUserResponse> getAllUsers();

    UserResponse getUserById(UUID id);

    LoginResponse login(LoginUserCommand loginCommand);
}
