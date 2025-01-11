package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.LoginUserCommand;
import com.fns.user.service.domain.dto.get.LoginResponse;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.dto.get.GetAllUserResponse;
import com.fns.user.service.domain.entity.Location;
import com.fns.user.service.domain.entity.Role;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.mapper.UserDataMapper;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import com.fns.user.service.domain.ports.output.repository.UserRoleRepository;
import com.fns.user.service.domain.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class UserCommandHandler {

    private final UserHelper userHelper;

    private final UserDataMapper userDataMapper;

    private final UserGetAllHandler userGetAllHandler;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    private final TokenUtil tokenUtil;

    public UserCommandHandler(UserHelper userHelper, UserDataMapper userDataMapper, UserGetAllHandler userGetAllHandler, UserRoleRepository userRoleRepository, UserRepository userRepository, TokenUtil tokenUtil) {
        this.userHelper = userHelper;
        this.userDataMapper = userDataMapper;
        this.userGetAllHandler = userGetAllHandler;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    public UserResponse createUser(CreateUserCommand createUserCommand) {
        /*
            Event here if any
         */
        return  userHelper.userCreateMethod(createUserCommand);

    }

    public UserResponse editUser(UUID id, CreateUserCommand createUserCommand) {
        User exitingUser = getUser(id);

        // Update other fields
        exitingUser.setName(createUserCommand.getName());
        exitingUser.setUser_name(createUserCommand.getUser_name());
        exitingUser.setEmail(createUserCommand.getEmail());
        exitingUser.setProfile_picture(createUserCommand.getProfile_picture());
        exitingUser.setPhone_number(createUserCommand.getPhone_number());
        exitingUser.setIs_verified(createUserCommand.getIs_verified());

        // Hash the password before updating
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(createUserCommand.getPassword());
        exitingUser.setPassword(hashedPassword);

        // Save updated user information
        UserResponse editUser = userHelper.userEditMethod(exitingUser);

        // Return the updated user as a response
        return userDataMapper.userToUserResponse(createUserCommand);
    }

    public UserResponse getUserById(UUID id) {
        User user = getUser(id);
        return userDataMapper.userResponseFromUser(user);
    }

    public LoginResponse login(LoginUserCommand loginUserCommand) {
        User user = userRepository.findByUsernameOrEmail(loginUserCommand.getUsernameEmail(), loginUserCommand.getPassword());
        Role role = userRoleRepository.getRoleById(user.getRole_id());

        // Generate the token (e.g., JWT)
        String accessToken = tokenUtil.generateToken(user, role);

        // Return the LoginResponse
        return new LoginResponse("successfully login", accessToken, true);
    }


    private User getUser(UUID id) {
        // Implement your logic here
        return userRepository.getUserById(id);
    }

    public Page<GetAllUserResponse> getAllUsers(Integer page, Integer  pageSize, UUID role, String name) {
        return userGetAllHandler.getAllUser(page, pageSize, role, name);
    }
}
