package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductAndColor;

public interface ProductAndColorRepository {
    void save(ProductAndColor productAndColor);
}
