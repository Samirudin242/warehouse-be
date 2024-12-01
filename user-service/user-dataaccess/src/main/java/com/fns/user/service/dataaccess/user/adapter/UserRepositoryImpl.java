package com.fns.user.service.dataaccess.user.adapter;

import com.fns.user.service.dataaccess.user.entity.UserEntity;
import com.fns.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.fns.user.service.dataaccess.user.repository.UserJpaRepository;
import com.fns.user.service.domain.UserGetAllHandler;
import com.fns.user.service.domain.dto.create.GetAllUserResponse;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.mapper.UserDataMapper;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserRepositoryImpl implements UserRepository { // it will be provide the implementations for all method that we define in interface

    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserDataAccessMapper userDataAccessMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
    }



    @Override
    public User save(User user) {
        try {
            log.info(user.toString());
            UserEntity userEntity = userDataAccessMapper.userToUserEntity(user);

            UserEntity savedEntity = userJpaRepository.save(userEntity);
            log.info("Saved Entity: {}", savedEntity);
            return userDataAccessMapper.userEntityToUser(savedEntity);
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage());
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public List<GetAllUserResponse> getAllUsers() {
        try {
            List<UserEntity> userEntities = userJpaRepository.findAll();

            if (userEntities.stream().anyMatch(userEntity -> userEntity.getId() == null)) {
                throw new IllegalStateException("One or more UserEntities have null IDs");
            }

            List<User> users = userDataAccessMapper.userEntitiesToUsers(userEntities);

            return users.stream()
                    .map(user -> new GetAllUserResponse(
                            user.getIdUser() != null ? user.getIdUser() : UUID.fromString("N/A"),
                            user.getUsername(),
                            user.getEmail(),
                            user.getRole()
                    ))
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            log.error("Error fetching all users: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching all users", e);
        }
    }

}
