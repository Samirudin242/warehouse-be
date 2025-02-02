package com.fns.user.service.domain.event;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserEditEvent extends UserEvent {

    private final DomainEventPublisher<UserEditEvent> eventPublisher;

    public UserEditEvent(User user, ZonedDateTime time, DomainEventPublisher<UserEditEvent> eventPublisher) {
        super(user, time);
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void fire() {
        eventPublisher.publish(this);
    }
}
