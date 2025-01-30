package com.fns.product.service.domain.ports.output.message;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.product.service.domain.event.ProductCreatedEvent;
import org.springframework.stereotype.Component;


public interface ProductMessagePublisher extends DomainEventPublisher<ProductCreatedEvent> {
}
