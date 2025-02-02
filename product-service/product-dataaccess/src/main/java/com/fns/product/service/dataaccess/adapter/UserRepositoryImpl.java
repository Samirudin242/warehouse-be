package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.UserEntity;
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

    @Override
    public void editUser(UserModel user) {
        UserEntity userEntity = userJpaRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone_number(user.getPhoneNumber());
        userEntity.setProfile_picture(user.getProfilePicture());
        userEntity.setUser_name(user.getUserName());
        userJpaRepository.save(userEntity);
    }
}
