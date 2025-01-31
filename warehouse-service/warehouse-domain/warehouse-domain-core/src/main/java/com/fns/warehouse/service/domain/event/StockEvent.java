package com.fns.warehouse.service.domain.event;

import com.fns.domain.event.DomainEvent;
import com.fns.warehouse.service.domain.entity.Stock;
import com.fns.warehouse.service.domain.entity.Warehouse;

import java.time.ZonedDateTime;

public class StockEvent implements DomainEvent<Stock> {

    private final Stock stock;
    private final ZonedDateTime time;

    public StockEvent(Stock stock, ZonedDateTime time) {
        this.stock = stock;
        this.time = time;
    }

    @Override
    public Stock getEntity() {
        return stock;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return time;
    }

    @Override
    public void fire() {

    }
}
