package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductPrices;

import java.util.UUID;


public interface ProductPricesRepository {
    ProductPrices savePrice(ProductPrices productPrices);

    ProductPrices getPriceByProductId(UUID productId);
}
