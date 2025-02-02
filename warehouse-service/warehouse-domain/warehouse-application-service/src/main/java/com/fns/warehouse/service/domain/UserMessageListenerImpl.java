package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.message.UserModel;
import com.fns.warehouse.service.domain.entity.User;
import com.fns.warehouse.service.domain.mapper.UserDataMapper;
import com.fns.warehouse.service.domain.ports.input.message.UserMessageListener;
import com.fns.warehouse.service.domain.ports.output.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserMessageListenerImpl implements UserMessageListener {

    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;

    public UserMessageListenerImpl(UserRepository userRepository, UserDataMapper userDataMapper) {
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    @Override
    public void savedUser(UserModel userModel) {
        try {
            log.info("Saved user user model {}", userModel);
            // Map UserModel to User entity
            User user = userDataMapper.userModelToUser(userModel);

            // Save the user entity
            userRepository.save(user);
            log.info("User saved successfully: {}", user);

        } catch (Exception e) {
            log.error("Error saving user to the database", e);
        }
    }

    @Override
    public void editedUser(UserModel userModel) {
        try {
            log.info("Saved edit user model {}", userModel);
            // Map UserModel to User entity
            User user = userDataMapper.userModelToUser(userModel);

            // Save the user entity
            userRepository.edit(user);
            log.info("User edit successfully: {}", user);

        } catch (Exception e) {
            log.error("Error saved user to the database", e);
        }
    }
}
