package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.entity.User;
import com.fns.warehouse.service.domain.ports.output.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserCommandHandler {

    private final UserRepository userRepository;

    public UserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Failed to save user: {}", user, e);
            throw new RuntimeException(e);
        }
    }

}
