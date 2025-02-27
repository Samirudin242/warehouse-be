package com.fns.user.service.domain.ports.input.service;

import com.fns.user.service.domain.dto.create.CreateLocation;
import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.EditUserCommand;
import com.fns.user.service.domain.dto.create.LoginUserCommand;
import com.fns.user.service.domain.dto.get.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserApplicationService {
    UserResponse createUser(@Valid CreateUserCommand createUserCommand);

    UserResponse editUser(@Valid EditUserCommand editUserCommand);

    UserResponse editUser(UUID id, CreateUserCommand createUserCommand);

    Page<GetAllUserResponse> getAllUsers(int page, int pageSize, UUID role, String name);

    UserResponse getUserById(UUID id);

    LoginResponse login(LoginUserCommand loginCommand);

    ProfilePhotoResponse uploadProfilePhoto(MultipartFile file) throws IOException;

    List<RoleResponse> getAllRoles();

    List<LocationResponse> getAllLocation(UUID userId);

    LocationResponse createLocation(@Valid CreateLocation createLocation);

    UserCount getAllUser();

}
