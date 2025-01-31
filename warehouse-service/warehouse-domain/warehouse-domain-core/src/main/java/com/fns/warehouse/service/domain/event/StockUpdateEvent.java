package com.fns.warehouse.service.domain.event;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.entity.Stock;

import java.time.ZonedDateTime;

public class StockUpdateEvent extends StockEvent {

    private final DomainEventPublisher<StockUpdateEvent> eventPublisher;


    public StockUpdateEvent(Stock stock, ZonedDateTime time, DomainEventPublisher<StockUpdateEvent> eventPublisher) {
        super(stock, time);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void fire() {
        eventPublisher.publish(this);
    }
}
