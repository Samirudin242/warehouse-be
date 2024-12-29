package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.LoginUserCommand;
import com.fns.user.service.domain.dto.get.LoginResponse;
import com.fns.user.service.domain.dto.get.RoleResponse;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.dto.get.GetAllUserResponse;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserCommandHandler userCommandHandler;

    private final UserRepository userRepository;

    public UserApplicationServiceImpl(UserCommandHandler userCommandHandler, UserRepository userRepository) {
        this.userCommandHandler = userCommandHandler;
        this.userRepository = userRepository;
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

    @Override
    public String uploadProfilePhoto(MultipartFile file) throws IOException {
        return userRepository.uploadUserPhoto(file);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return userRepository.getAllRoles();
    }
}
