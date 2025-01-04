package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.entity.UserEntity;
import com.fns.warehouse.service.dataaccess.mapper.UserDataAccessMapper;
import com.fns.warehouse.service.dataaccess.repository.UserJpaRepository;
import com.fns.warehouse.service.domain.entity.User;
import com.fns.warehouse.service.domain.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserDataAccessMapper userDataAccessMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
    }


    @Override
    public User save(User user) {
        UserEntity userEntity = userDataAccessMapper.userToUserEntity(user);
        return userDataAccessMapper.userEntityToUser(userJpaRepository.save(userEntity));
    }
}
