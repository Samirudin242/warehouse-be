package com.fns.user.service.domain.ports.input.service;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.CreateUserResponse;
import com.fns.user.service.domain.dto.create.GetAllUserResponse;

import javax.validation.Valid;
import java.util.List;

public interface UserApplicationService {
    CreateUserResponse createUser(@Valid CreateUserCommand createUserCommand);

    List<GetAllUserResponse> getAllUsers();

}
