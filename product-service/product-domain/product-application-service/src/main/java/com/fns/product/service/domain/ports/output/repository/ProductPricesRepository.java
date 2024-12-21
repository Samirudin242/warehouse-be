package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductPrices;


public interface ProductPricesRepository {
    ProductPrices savePrice(ProductPrices productPrices);
}
