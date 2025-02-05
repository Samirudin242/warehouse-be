package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateLocation;
import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.EditUserCommand;
import com.fns.user.service.domain.dto.create.LoginUserCommand;
import com.fns.user.service.domain.dto.get.*;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    private final LocationCommandHandler locationCommandHandler;
    private final UserRepository userRepository;

    public UserApplicationServiceImpl(UserCommandHandler userCommandHandler, LocationCommandHandler locationCommandHandler, UserRepository userRepository) {
        this.userCommandHandler = userCommandHandler;
        this.locationCommandHandler = locationCommandHandler;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(CreateUserCommand createUserCommand) {
        return userCommandHandler.createUser(createUserCommand);
    }

    @Override
    public UserResponse editUser(EditUserCommand editUserCommand) {
        return userCommandHandler.editUser(editUserCommand);
    }

    @Override
    public UserResponse editUser(UUID id, CreateUserCommand createUserCommand) {
        return userCommandHandler.editUser(id, createUserCommand);
    }

    @Override
    public Page<GetAllUserResponse> getAllUsers(int page, int pageSize, UUID role, String name) {
        return userCommandHandler.getAllUsers(page, pageSize, role, name);
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
    public ProfilePhotoResponse uploadProfilePhoto(MultipartFile file) throws IOException {
        return userRepository.uploadUserPhoto(file);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return userRepository.getAllRoles();
    }

    @Override
    public List<LocationResponse> getAllLocation(UUID userId) {
        return locationCommandHandler.getAllLocation(userId);
    }

    @Override
    public LocationResponse createLocation(CreateLocation createLocation) {
        return locationCommandHandler.createLocation(createLocation);
    }

    @Override
    public UserCount getAllUser() {
        return userRepository.getAllUser();
    }
}
