package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.get.UserAlreadyExistsException;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.mapper.UserDataMapper;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j // this from lombok to get access log object
@Component // register the class as a "Spring-managed bean" which is enabling it to be injected into another components
public class UserHelper {

    private final UserRepository userRepository; // this is just declare, different with initialize

    private final UserDataMapper userDataMapper;

    public UserHelper(// this is called constructor, which is to initialize the object's state, that sets up the initial values for the object attributes
                      UserDomainService userDomainService,
                      UserRepository userRepository,
                      UserDataMapper userDataMapper
            ) {
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    @Transactional
    public UserResponse userCreateMethod(CreateUserCommand createUserCommand) {
        UserResponse user = userDataMapper.userToUserResponse(createUserCommand);

        log.info("user from helper, {}", user);
        User userEntity = userDataMapper.createUser(user);


        saveUser(userEntity);

        return user;
    }

    public UserResponse userEditMethod(User user) {
        UserResponse userEdit = userDataMapper.userResponseFromUser(user);

        User userEntity = userDataMapper.createUser(userEdit);

        saveUser(userEntity);

        return userEdit;
    }



    private void saveUser(User user) {
        try {
            User userResult = userRepository.save(user);

            if (userResult == null) {
                log.error("Failed to save user. The user entity was not persisted.");
                throw new RuntimeException("Failed to save user");
            }

            log.info("Successfully saved user with id from create helper: {}", userResult.getId());
        } catch (Exception e) {
            log.error("Error while saving user: {}", e.getMessage());
            throw new UserAlreadyExistsException("User with the same username or email already exists");
        }
    }

}
