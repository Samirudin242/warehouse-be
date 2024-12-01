package com.fns.warehouse.service.domain.event;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.entity.Warehouse;
import java.time.ZonedDateTime;

public class WarehouseCreatedEvent extends WarehouseEvent {
    private final DomainEventPublisher<WarehouseCreatedEvent> eventPublisher;

    public WarehouseCreatedEvent(Warehouse warehouse, ZonedDateTime createdAt, DomainEventPublisher<WarehouseCreatedEvent> eventPublisher) {
        super(warehouse, createdAt);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void fire() {
        eventPublisher.publish(this);
    }
}
