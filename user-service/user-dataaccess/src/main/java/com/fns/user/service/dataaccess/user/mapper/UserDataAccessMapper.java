package com.fns.user.service.dataaccess.user.mapper;

import com.fns.domain.valueobject.EmailAddress;
import com.fns.domain.valueobject.UserId;
import com.fns.user.service.dataaccess.user.entity.UserEntity;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.valueObject.Role;
import com.fns.user.service.domain.valueObject.UserName;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataAccessMapper {

    public UserEntity userToUserEntity(User user) {
        UserEntity userEntity = UserEntity.builder()
//                .id(user.getId().getValue())
                .userName(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .userRole(user.getRole())
                .build();
        return userEntity;
    }

    public User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .emailAddress(userEntity.getEmail())
                .role(userEntity.getUserRole())
                .build();
    }

    public List<User> userEntitiesToUsers(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(this::userEntityToUser)
                .collect(Collectors.toList());
    }

}
