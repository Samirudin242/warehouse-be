package com.fns.user.service.domain.ports.output.message;


import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.user.service.domain.event.UserCreatedEvent;
import org.springframework.stereotype.Component;


@Component
public interface UserMessagePublisher extends DomainEventPublisher<UserCreatedEvent> {

}
