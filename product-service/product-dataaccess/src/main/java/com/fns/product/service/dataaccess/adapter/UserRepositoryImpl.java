package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.mapper.UserDataMapper;
import com.fns.product.service.dataaccess.repository.UserJpaRepository;
import com.fns.product.service.domain.dto.message.UserModel;
import com.fns.product.service.domain.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserDataMapper userDataMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserDataMapper userDataMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataMapper = userDataMapper;
    }


    @Override
    public void saveUser(UserModel user) {
        userJpaRepository.save(userDataMapper.userFromUserModel(user));
    }
}
