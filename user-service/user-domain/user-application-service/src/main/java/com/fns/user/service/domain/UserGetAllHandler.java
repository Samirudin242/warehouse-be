package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.get.GetAllUserResponse;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserGetAllHandler {

    private final UserRepository userRepository;

    public UserGetAllHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<GetAllUserResponse> getAllUser(Integer page, Integer pageSize, UUID role, String name) {
        return userRepository.getAllUsers(page, pageSize, role, name);
    }

}
