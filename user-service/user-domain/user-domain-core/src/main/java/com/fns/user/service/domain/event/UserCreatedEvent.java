package com.fns.user.service.domain.event;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserCreatedEvent extends  UserEvent {

    private final DomainEventPublisher<UserCreatedEvent> eventPublisher;

    public UserCreatedEvent(User user, ZonedDateTime time, DomainEventPublisher<UserCreatedEvent> eventPublisher) {
        super(user, time);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void fire() {
        eventPublisher.publish(this);
    }
}
