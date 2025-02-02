package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.entity.UserEntity;
import com.fns.warehouse.service.dataaccess.mapper.UserDataAccessMapper;
import com.fns.warehouse.service.dataaccess.repository.UserJpaRepository;
import com.fns.warehouse.service.domain.entity.User;
import com.fns.warehouse.service.domain.ports.output.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
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

    @Override
    public User edit(User user) {
        UserEntity userEntity = userJpaRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone_number(user.getPhone_number());
        userEntity.setProfile_picture(user.getProfile_picture());
        userEntity.setUser_name(user.getUser_name());

        userJpaRepository.save(userEntity);

        return userDataAccessMapper.userEntityToUser(userJpaRepository.save(userEntity));

    }
}
