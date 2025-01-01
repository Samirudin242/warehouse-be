package com.fns.user.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.user.service.domain.event.UserCreatedEvent;
import com.fns.user.service.domain.exception.UserDomainException;

public interface UserDomainService {
    UserCreatedEvent createUser(String name, DomainEventPublisher<UserCreatedEvent> publisher) throws UserDomainException;

}
