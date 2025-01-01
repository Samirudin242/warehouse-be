package com.fns.user.service.domain.event;

import com.fns.domain.event.DomainEvent;
import com.fns.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public abstract class UserEvent implements DomainEvent<User> {
    private final User user;
    private final ZonedDateTime time;


    protected UserEvent(User user, ZonedDateTime time) {
        this.user = user;
        this.time = time;
    }

    @Override
    public User getEntity() {
        return user;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return time;
    }
}
