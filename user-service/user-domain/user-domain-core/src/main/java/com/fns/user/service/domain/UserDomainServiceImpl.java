package com.fns.user.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.event.UserCreatedEvent;
import com.fns.user.service.domain.event.UserEditEvent;
import com.fns.user.service.domain.exception.UserDomainException;

import java.time.ZonedDateTime;
import java.util.UUID;

public class UserDomainServiceImpl implements UserDomainService {

    @Override
    public UserCreatedEvent createUser(UUID id, String name, String username, String email, String phoneNumber, String profilePicture, String address, String cityId, String provinceId, UUID roleId, DomainEventPublisher<UserCreatedEvent> publisher) throws UserDomainException {
        User user = User.builder()
                .id(id)
                .name(name)
                .user_name(username)
                .email(email)
                .phone_number(phoneNumber)
                .profile_picture(profilePicture)
                .address(address)
                .city_id(cityId)
                .province_id(provinceId)
                .role_id(roleId)
                .is_verified(true)
                .build();

        return new UserCreatedEvent(user, ZonedDateTime.now(), publisher);
    }

    @Override
    public UserEditEvent editUser(UUID id, String name, String username, String email, String phoneNumber, String profilePicture, UUID roleId, Boolean isVerified, DomainEventPublisher<UserEditEvent> publisher) throws UserDomainException {
        User user = User.builder()
                .id(id)
                .name(name)
                .user_name(username)
                .email(email)
                .phone_number(phoneNumber)
                .profile_picture(profilePicture)
                .role_id(roleId)
                .is_verified(true)
                .build();

        return new UserEditEvent(user, ZonedDateTime.now(), publisher);

    }
}
