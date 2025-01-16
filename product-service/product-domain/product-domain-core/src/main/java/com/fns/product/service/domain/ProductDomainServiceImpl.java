package com.fns.product.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.event.ProductCreatedEvent;
import com.fns.product.service.domain.exception.ProductDomainException;
import com.fns.product.service.domain.valueObject.Price;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ProductDomainServiceImpl implements ProductDomainService {

    @Override
    public ProductCreatedEvent createProduct(UUID id, String sku, String name, String slug, String description, String gender, Double price, UUID brandId, UUID productCategoryId, UUID sizeId, UUID colorId, String imageUrl, UUID warehouseId, Integer stock, DomainEventPublisher<ProductCreatedEvent> eventPublisher) throws ProductDomainException {
        Product product = Product.builder()
                .id(id)
                .sku(sku)
                .name(name)
                .slug(slug)
                .description(description)
                .gender(gender)
                .price(price)
                .brandId(brandId)
                .productCategoryId(productCategoryId)
                .imageUrl(imageUrl)
                .warehouseId(warehouseId)
                .stock(stock)
                .build();

        return new ProductCreatedEvent(product, ZonedDateTime.now(), eventPublisher);

    }

}
