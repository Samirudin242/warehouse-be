package com.fns.product.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.product.service.domain.event.ProductCreatedEvent;
import com.fns.product.service.domain.exception.ProductDomainException;
import com.fns.product.service.domain.valueObject.Price;

import java.util.UUID;

public interface ProductDomainService {
    ProductCreatedEvent createProduct(
            UUID id,
            String sku,
            String name,
            String slug,
            String description,
            String gender,
            Price price,
            UUID brandId,
            UUID productCategoryId,
            UUID sizeId,
            UUID colorId,
            String imageUrl,
            UUID warehouseId,
            Integer stock,
            DomainEventPublisher<ProductCreatedEvent> eventPublisher) throws ProductDomainException;
}
