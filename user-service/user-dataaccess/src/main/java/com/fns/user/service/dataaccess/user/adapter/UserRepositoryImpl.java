package com.fns.user.service.dataaccess.user.adapter;

import com.fns.user.service.dataaccess.user.entity.LocationEntity;
import com.fns.user.service.dataaccess.user.entity.UserEntity;
import com.fns.user.service.dataaccess.user.entity.UserRoleEntity;
import com.fns.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.fns.user.service.dataaccess.user.mapper.UserRoleMapper;
import com.fns.user.service.dataaccess.user.repository.LocationJpaRepository;
import com.fns.user.service.dataaccess.user.repository.UserJpaRepository;
import com.fns.user.service.dataaccess.user.repository.UserRoleJpaRepository;
import com.fns.user.service.domain.dto.create.EditUserCommand;
import com.fns.user.service.domain.dto.get.GetAllUserResponse;
import com.fns.user.service.domain.dto.get.ProfilePhotoResponse;
import com.fns.user.service.domain.dto.get.RoleResponse;
import com.fns.user.service.domain.dto.get.UserAlreadyExistsException;
import com.fns.user.service.domain.entity.Location;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@Slf4j
@Component
public class UserRepositoryImpl implements UserRepository { // it will be provide the implementations for all method that we define in interface

    private final UserJpaRepository userJpaRepository;
    private final UserRoleJpaRepository userRoleJpaRepository;
    private final LocationJpaRepository locationJpaRepository;

    private final UserDataAccessMapper userDataAccessMapper;
    private final UserRoleMapper userRoleMapper;

    private final Storage storage;

    @Value("${google.cloud.storage.bucket-name}")
    private String bucketName;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserRoleJpaRepository userRoleJpaRepository, LocationJpaRepository locationJpaRepository, UserDataAccessMapper userDataAccessMapper, UserRoleMapper userRoleMapper, Storage storage) {
        this.userJpaRepository = userJpaRepository;
        this.userRoleJpaRepository = userRoleJpaRepository;
        this.locationJpaRepository = locationJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
        this.userRoleMapper = userRoleMapper;
        this.storage = storage;
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

            UserEntity userEntity = userDataAccessMapper.userToUserEntity(user);

            UserEntity savedEntity = userJpaRepository.save(userEntity);
            log.info("Saved Entity: {}", savedEntity);

            // Map the saved entity back to a domain user
            return userDataAccessMapper.userEntityToUserGet(savedEntity);
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage());
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public User edit(EditUserCommand editUserCommand) {
        UserEntity userEntity = userJpaRepository.findById(editUserCommand.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + editUserCommand.getId()));

        UserRoleEntity roleEntity = userRoleJpaRepository.findById(editUserCommand.getRole_id())
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + editUserCommand.getRole_id()));


        userEntity.setName(editUserCommand.getName());
        userEntity.setUser_name(editUserCommand.getUser_name());
        userEntity.setEmail(editUserCommand.getEmail());
        userEntity.setProfile_picture(editUserCommand.getProfile_url());
        userEntity.setPhone_number(editUserCommand.getPhone_number());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setUser_role(roleEntity);
        UserEntity savedEntity = userJpaRepository.save(userEntity);

        return userDataAccessMapper.userEntityToUser(savedEntity);
    }


    @Override
    public Page<GetAllUserResponse> getAllUsers(Integer page, Integer size, UUID role, String name) {
        try {

            Pageable pageable = PageRequest.of(page, size);

            Page<UserEntity> userEntitiesPage;
            if (role == null && (name == null )) {
                userEntitiesPage = userJpaRepository.findAll(pageable);
            } else if(name == null) {
                userEntitiesPage = userJpaRepository.findByRoleId(role, pageable);
            } else {
                userEntitiesPage = userJpaRepository.findByRoleIdAndName(role, name, pageable);
            }

            // Convert UserEntity to User
            List<User> users = userDataAccessMapper.userEntitiesToUsers(userEntitiesPage.getContent());

            // Convert User to GetAllUserResponse
            List<GetAllUserResponse> userResponses = users.stream()
                    .map(user -> new GetAllUserResponse(
                            user.getId() != null ? user.getId() : UUID.fromString("N/A"),
                            user.getName(),
                            user.getUser_name(),
                            user.getEmail(),
                            user.getRole_id().toString(),
                            user.getRole_name(),
                            user.getAddress(),
                            user.getCity(),
                            user.getProvince(),
                            user.getProfile_picture()
                    ))
                    .collect(Collectors.toList());

            // Return a paginated response
            return new PageImpl<>(userResponses, pageable, userEntitiesPage.getTotalElements());
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

    @Override
    public ProfilePhotoResponse uploadUserPhoto(MultipartFile file) throws IOException {
        // Validate file size
        if (file.getSize() > 1_048_576) {
            throw new IllegalArgumentException("File size exceeds 1 MB limit");
        }

        // Validate file content type
        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
            throw new IllegalArgumentException("Invalid file type. Only image files are allowed.");
        }

        // Generate a unique timestamp
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        // Create a unique file name using the timestamp
        String fileName = String.format("users/%s_%s", timestamp, file.getOriginalFilename());

        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        // Return the public URL of the uploaded file
        return ProfilePhotoResponse.builder()
                .url(String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName))
                .message("Successfully uploaded file")
                .build();
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<UserRoleEntity> userRoles = userRoleJpaRepository.findAll();
        return userRoleMapper.userRoleEntitiesToResponse(userRoles);
    }


}
