package com.fns.user.service.domain.mapper;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.get.LocationResponse;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.entity.Location;
import com.fns.user.service.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserDataMapper {

    public UserResponse userToUserResponse(CreateUserCommand createUserCommand) {
        return UserResponse.builder()
//                .id(createUserCommand.getId())
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

    public User createUser(UserResponse createUserResponse) {
        return User.builder()
                .name(createUserResponse.getName())
                .user_name(createUserResponse.getUser_name())
                .email(createUserResponse.getEmail())
                .profile_picture(createUserResponse.getProfile_picture())
                .role_id(createUserResponse.getRole_id())
                .password(createUserResponse.getPassword())
                .phone_number(createUserResponse.getPhone_number())
                .is_verified(createUserResponse.getIs_verified())
                .build();
    }

    public UserResponse userResponseFromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .user_name(user.getUser_name())
                .email(user.getEmail())
                .password(user.getPassword())
                .profile_picture(user.getProfile_picture())
                .phone_number(user.getPhone_number())
                .is_verified(user.getIs_verified())
                .role_id(user.getRole_id())
                .build();
    }

    public User userFromCreateCommand(CreateUserCommand createUserCommand) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(createUserCommand.getPassword());
        return User.builder()
                .name(createUserCommand.getName())
                .user_name(createUserCommand.getUser_name())
                .email(createUserCommand.getEmail())
                .password(hashedPassword)
                .profile_picture(createUserCommand.getProfile_picture())
                .phone_number(createUserCommand.getPhone_number())
                .is_verified(createUserCommand.getIs_verified())
                .role_id(createUserCommand.getRole_id())
                .build();
    }

    public List<LocationResponse> getLocationResponse(List<Location> locations) {
        return locations.stream()
                .map(location -> LocationResponse.builder()
                        .id(location.getId())
                        .province(location.getProvince())
                        .provinceId(location.getProvince_id())
                        .city(location.getCity())
                        .cityId(location.getCity_id())
                        .address(location.getAddress())
                        .postal_code(location.getPostal_code())
                        .name(location.getName())
                        .phone_number(location.getPhone_number())
                        .build())
                .collect(Collectors.toList());
    }
}

