package com.fns.product.service.domain.event;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.product.service.domain.entity.Product;

import java.time.ZonedDateTime;

public class ProductCreatedEvent extends ProductEvent {

    private final DomainEventPublisher<ProductCreatedEvent> eventPublisher;

    public ProductCreatedEvent(Product product, ZonedDateTime time, DomainEventPublisher<ProductCreatedEvent> eventPublisher) {
        super(product, time);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void fire() {
        eventPublisher.publish(this);
    }
}
