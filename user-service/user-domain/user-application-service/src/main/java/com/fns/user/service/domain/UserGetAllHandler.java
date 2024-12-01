package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.GetAllUserResponse;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGetAllHandler {

    private final UserRepository userRepository;

    public UserGetAllHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<GetAllUserResponse> getAllUser() {
        return userRepository.getAllUsers();
    }

}
