package com.fns.product.service.domain.event;

import com.fns.product.service.domain.entity.Product;
import com.fns.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public abstract class ProductEvent implements DomainEvent<Product> {
    private final Product product;
    private final ZonedDateTime time;


    protected ProductEvent(Product product, ZonedDateTime time) {
        this.product = product;
        this.time = time;
    }

    @Override
    public Product getEntity() {
        return product;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return time;
    }

}
