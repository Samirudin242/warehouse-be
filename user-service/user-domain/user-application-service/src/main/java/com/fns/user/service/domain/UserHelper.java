package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.get.UserAlreadyExistsException;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.entity.Location;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.event.UserCreatedEvent;
import com.fns.user.service.domain.mapper.UserDataMapper;
import com.fns.user.service.domain.ports.output.message.UserMessagePublisher;
import com.fns.user.service.domain.ports.output.repository.LocationRepository;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j // this from lombok to get access log object
@Component // register the class as a "Spring-managed bean" which is enabling it to be injected into another components
public class UserHelper {

    private final UserRepository userRepository; // this is just declare, different with initialize
    private final LocationRepository locationRepository;
    private final UserMessagePublisher userMessagePublisher;
    private final UserDomainService userDomainService;

    private final UserDataMapper userDataMapper;

    public UserHelper(// this is called constructor, which is to initialize the object's state, that sets up the initial values for the object attributes
                      UserDomainService userDomainService,
                      UserRepository userRepository, LocationRepository locationRepository, UserMessagePublisher userMessagePublisher, UserDomainService userDomainService1,
                      UserDataMapper userDataMapper
            ) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.userMessagePublisher = userMessagePublisher;
        this.userDomainService = userDomainService1;
        this.userDataMapper = userDataMapper;
    }

    @Transactional
    public UserResponse userCreateMethod(CreateUserCommand createUserCommand) {

        User userEntity = userDataMapper.userFromCreateCommand(createUserCommand);

        User savedUser = saveUser(userEntity);

        log.info("Saved user: " + savedUser);


        Location location = Location.builder()
                .user_id(savedUser.getId())
                .city_id(createUserCommand.getCity_id())
                .province_id(createUserCommand.getProvince_id())
                .city(createUserCommand.getCity())
                .province(createUserCommand.getProvince())
                .address(createUserCommand.getAddress())
                .postal_code(createUserCommand.getPostal_code())
                .build();

        Location savedLocation = saveLocation(location);

        UserCreatedEvent userEvent = userDomainService.createUser(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getUser_name(),
                savedUser.getEmail(),
                savedUser.getPhone_number(),
                savedUser.getProfile_picture(),
                savedLocation.getAddress(),
                savedLocation.getCity_id(),
                savedLocation.getProvince_id(),
                savedUser.getRole_id(),
                userMessagePublisher
        );

        log.info("User event saved, {}", userEvent);

        //publish the user
        userMessagePublisher.publish(userEvent);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(createUserCommand.getName())
                .user_name(createUserCommand.getUser_name())
                .email(createUserCommand.getEmail())
                .profile_picture(createUserCommand.getProfile_picture())
                .phone_number(createUserCommand.getPhone_number())
                .is_verified(createUserCommand.getIs_verified())
                .role_id(createUserCommand.getRole_id())
                .city_id(createUserCommand.getCity_id())
                .province_id(createUserCommand.getProvince_id())
                .city(createUserCommand.getCity())
                .province(createUserCommand.getProvince())
                .address(createUserCommand.getAddress())
                .postal_code(createUserCommand.getPostal_code())
                .build();
    }

    public UserResponse userEditMethod(User user) {
        UserResponse userEdit = userDataMapper.userResponseFromUser(user);

        User userEntity = userDataMapper.createUser(userEdit);

        saveUser(userEntity);

        return userEdit;
    }



    private User saveUser(User user) {
        try {
            User userResult = userRepository.save(user);

            if (userResult == null) {
                log.error("Failed to save user. The user entity was not persisted.");
                throw new RuntimeException("Failed to save user");
            }

            return userResult;

        } catch (Exception e) {
            log.error("Error while saving user: {}", e.getMessage());
            throw new UserAlreadyExistsException(e.getMessage());
        }
    }

    private Location saveLocation(Location location) {
        try {
            Location locationResult = locationRepository.saveLocation(location);

            if (locationResult == null) {
                log.error("Failed to save location. The location entity was not persisted.");
                throw new RuntimeException("Failed to save location");
            }

            return locationResult;
        } catch (Exception e) {
            log.error("Error while saving location: {}", e.getMessage());
            throw new RuntimeException("Failed to save location");
        }
    }

}
