package com.fns.user.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.user.service.domain.entity.User;
import com.fns.user.service.domain.event.UserCreatedEvent;
import com.fns.user.service.domain.exception.UserDomainException;

import java.time.ZonedDateTime;

public class UserDomainServiceImpl implements UserDomainService{

    @Override
    public UserCreatedEvent createUser(String name, DomainEventPublisher<UserCreatedEvent> publisher) throws UserDomainException {
        User user = User.builder()
                .name(name)
                .build();

        return new UserCreatedEvent(user, ZonedDateTime.now(), publisher);
    }
}
