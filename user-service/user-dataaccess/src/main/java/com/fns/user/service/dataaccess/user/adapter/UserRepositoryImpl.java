package com.fns.user.service.dataaccess.user.adapter;

import com.fns.user.service.dataaccess.user.entity.UserEntity;
import com.fns.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.fns.user.service.dataaccess.user.repository.UserJpaRepository;
import com.fns.user.service.domain.dto.get.GetAllUserResponse;
import com.fns.user.service.domain.dto.get.UserAlreadyExistsException;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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
            log.info("Attempting to save user: {}", user);

            // Check if user already exists by username or email
            Optional<UserEntity> existingUser = userJpaRepository.findByUsernameOrEmail(user.getUser_name(), user.getEmail());

            if (existingUser.isPresent()) {
                throw new UserAlreadyExistsException("User with the same username or email already exists");
            }

            // Map the domain user to a JPA entity
            UserEntity userEntity = userDataAccessMapper.userToUserEntity(user);

            // Save the entity to the database
            UserEntity savedEntity = userJpaRepository.save(userEntity);
            log.info("Saved Entity: {}", savedEntity);

            // Map the saved entity back to a domain user
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
                            user.getId() != null ? user.getId() : UUID.fromString("N/A"),
                            user.getUser_name(),
                            user.getEmail(),
                            user.getRole_id()
                    ))
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            log.error("Error fetching all users: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching all users", e);
        }
    }

    @Override
    public User getUserById(UUID id) {
        UserEntity userEntity = userJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        return userDataAccessMapper.userEntityToUser(userEntity);
    }

    @Override
    public User findByUsernameOrEmail(String username, String password) {


        UserEntity userEntity = userJpaRepository.findByUsernameOrEmail(username);

        // Check if the user exists and the password matches
        if (userEntity == null || !BCrypt.checkpw(password, userEntity.getPassword())) {
            return null; // Invalid credentials
        }

        return userDataAccessMapper.userEntityToUser(userEntity);

    }

}
