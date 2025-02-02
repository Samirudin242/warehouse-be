package com.fns.user.service.domain.ports.output.message;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.user.service.domain.event.UserEditEvent;
import org.springframework.stereotype.Component;

@Component
public interface UserEditMessagePublisher extends DomainEventPublisher<UserEditEvent> {
}
