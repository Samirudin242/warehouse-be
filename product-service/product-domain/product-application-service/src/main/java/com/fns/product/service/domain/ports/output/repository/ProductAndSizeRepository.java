package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductAndSize;

import java.util.List;
import java.util.UUID;

public interface ProductAndSizeRepository {
    void save(ProductAndSize productAndSize);

    List<ProductAndSize> getAllByProductId(UUID productId);
}
