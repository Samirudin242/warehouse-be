package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.message.UserModel;
import com.fns.product.service.domain.ports.input.message.listener.UserMessageListener;
import com.fns.product.service.domain.ports.output.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserMessagingImpl implements UserMessageListener {

    private final UserRepository userRepository;

    public UserMessagingImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void savedUser(UserModel userModel) {
        userRepository.saveUser(userModel);
    }
}
