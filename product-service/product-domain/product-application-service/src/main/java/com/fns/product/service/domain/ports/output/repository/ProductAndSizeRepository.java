package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductAndSize;

public interface ProductAndSizeRepository {
    void save(ProductAndSize productAndSize);
}
