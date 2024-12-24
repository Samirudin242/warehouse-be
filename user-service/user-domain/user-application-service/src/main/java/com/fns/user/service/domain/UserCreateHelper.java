package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.CreateUserResponse;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.mapper.UserDataMapper;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j // this from lombok to get access log object
@Component // register the class as a "Spring-managed bean" which is enabling it to be injected into another components
public class UserCreateHelper {

    private final UserRepository userRepository; // this is just declare, different with initialize

    private final UserDataMapper userDataMapper;

    public UserCreateHelper(// this is called constructor, which is to initialize the object's state, that sets up the initial values for the object attributes
            UserDomainService userDomainService,
            UserRepository userRepository,
            UserDataMapper userDataMapper
            ) {
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    @Transactional
    public CreateUserResponse userCreateMethod(CreateUserCommand createUserCommand) {
        CreateUserResponse user = userDataMapper.userToCreateUserResponse(createUserCommand);

        log.info("user from helper, {}", user);
        User userEntity = userDataMapper.createUser(user);


        saveUser(userEntity);

        return user;
    }

    private void saveUser(User user) {
        try {
            log.info("this is user from create helper: {}", user);
            User userResult = userRepository.save(user);

            if (userResult == null) {
                log.error("Failed to save user. The user entity was not persisted.");
                throw new RuntimeException("Failed to save user");
            }

            log.info("this is user result", userResult);

            log.info("Successfully saved user with id from create helper: {}", userResult.getId());
        } catch (Exception e) {
            log.error("Error while saving user: {}", e.getMessage());
            throw new RuntimeException("Error while saving user", e); // Throw an exception if the save fails
        }
    }

}
