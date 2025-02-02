package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.EditUserCommand;
import com.fns.user.service.domain.dto.get.UserAlreadyExistsException;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.entity.Location;
import com.fns.user.service.domain.entity.Role;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.event.UserCreatedEvent;
import com.fns.user.service.domain.event.UserEditEvent;
import com.fns.user.service.domain.mapper.UserDataMapper;
import com.fns.user.service.domain.ports.output.message.UserEditMessagePublisher;
import com.fns.user.service.domain.ports.output.message.UserMessagePublisher;
import com.fns.user.service.domain.ports.output.repository.LocationRepository;
import com.fns.user.service.domain.ports.output.repository.UserRepository;
import com.fns.user.service.domain.ports.output.repository.UserRoleRepository;
import com.fns.user.service.domain.util.TokenUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j // this from lombok to get access log object
@Component // register the class as a "Spring-managed bean" which is enabling it to be injected into another components
public class UserHelper {

    private final UserRepository userRepository; // this is just declare, different with initialize
    private final LocationRepository locationRepository;
    private final UserMessagePublisher userMessagePublisher;
    private final UserEditMessagePublisher userEditMessagePublisher;
    private final UserDomainService userDomainService;

    private final UserDataMapper userDataMapper;
    private final UserRoleRepository userRoleRepository;
    private final TokenUtil tokenUtil;


    public UserHelper(// this is called constructor, which is to initialize the object's state, that sets up the initial values for the object attributes
                      UserDomainService userDomainService,
                      UserRepository userRepository, LocationRepository locationRepository, UserMessagePublisher userMessagePublisher, UserEditMessagePublisher userEditMessagePublisher, UserDomainService userDomainService1,
                      UserDataMapper userDataMapper, UserRoleRepository userRoleRepository, TokenUtil tokenUtil
    ) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.userMessagePublisher = userMessagePublisher;
        this.userEditMessagePublisher = userEditMessagePublisher;
        this.userDomainService = userDomainService1;
        this.userDataMapper = userDataMapper;
        this.userRoleRepository = userRoleRepository;
        this.tokenUtil = tokenUtil;
    }

    @Transactional
    public UserResponse userCreateMethod(CreateUserCommand createUserCommand) {

        User userEntity = userDataMapper.userFromCreateCommand(createUserCommand);

        User savedUser = saveUser(userEntity);

        Role role = userRoleRepository.getRoleById(savedUser.getRole_id());

        Location location = Location.builder()
                .user_id(savedUser.getId())
                .city_id(createUserCommand.getCity_id())
                .province_id(createUserCommand.getProvince_id())
                .city(createUserCommand.getCity())
                .province(createUserCommand.getProvince())
                .address(createUserCommand.getAddress())
                .postal_code(createUserCommand.getPostal_code())
                .latitude(createUserCommand.getLatitude())
                .longitude(createUserCommand.getLongitude())
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

        // Generate the token (e.g., JWT)
        String accessToken = tokenUtil.generateToken(savedUser, role);


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
                .accessToken(accessToken)
                .build();
    }

    @Transactional
    public UserResponse userEditMethod(EditUserCommand editUserCommand) {
        User editedUser = editUser(editUserCommand);

       UserEditEvent editEvent =  userDomainService.editUser(
                editedUser.getId(),
                editedUser.getName(),
                editedUser.getUser_name(),
                editedUser.getEmail(),
                editedUser.getPhone_number(),
                editedUser.getProfile_picture(),
                editedUser.getRole_id(),
                editedUser.getIs_verified(),
                userEditMessagePublisher
        );

        log.info("User event edit, {}", editEvent);
        //publish the user
        userEditMessagePublisher.publish(editEvent);

        return userDataMapper.userResponseFromUser(editedUser);

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

    private User editUser(EditUserCommand editUserCommand) {
        try {
            User userResult = userRepository.edit(editUserCommand);
            if (userResult == null) {
                throw new RuntimeException("Failed to edit user");
            }
            return userResult;
        } catch (Exception e) {
            log.error("Error while editing user: {}", e.getMessage());
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
