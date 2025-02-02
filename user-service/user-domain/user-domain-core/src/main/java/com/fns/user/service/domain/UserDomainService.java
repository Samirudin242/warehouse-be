package com.fns.user.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.user.service.domain.event.UserCreatedEvent;
import com.fns.user.service.domain.event.UserEditEvent;
import com.fns.user.service.domain.exception.UserDomainException;

import java.util.UUID;

public interface UserDomainService {
    UserCreatedEvent createUser(
            UUID id,
            String name,
            String username,
            String email,
            String phoneNumber,
            String profilePicture,
            String address,
            String cityId,
            String provinceId,
            UUID roleId,
            DomainEventPublisher<UserCreatedEvent> publisher) throws UserDomainException;

    UserEditEvent editUser(
            UUID id,
            String name,
            String username,
            String email,
            String phoneNumber,
            String profilePicture,
            UUID roleId,
            Boolean isVerified,
            DomainEventPublisher<UserEditEvent> publisher
    ) throws UserDomainException;
}
