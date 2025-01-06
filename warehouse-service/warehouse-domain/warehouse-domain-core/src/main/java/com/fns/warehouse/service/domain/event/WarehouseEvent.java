package com.fns.warehouse.service.domain.event;

import com.fns.domain.event.DomainEvent;
import com.fns.warehouse.service.domain.entity.Warehouse;

import java.lang.annotation.Annotation;
import java.time.ZonedDateTime;

public abstract class WarehouseEvent implements DomainEvent<Warehouse> {

    private final Warehouse warehouse;
    private final ZonedDateTime time;

    public WarehouseEvent(Warehouse warehouse, ZonedDateTime time) {
        this.warehouse = warehouse;
        this.time = time;
    }

    @Override
    public Warehouse getEntity() {
        return warehouse;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return time;
    }

    @Override
    public void fire() {

    }
}
