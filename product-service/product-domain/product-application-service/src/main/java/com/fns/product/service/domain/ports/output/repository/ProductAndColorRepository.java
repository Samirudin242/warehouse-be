package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductAndColor;

import java.util.List;
import java.util.UUID;

public interface ProductAndColorRepository {
    void save(ProductAndColor productAndColor);

    List<ProductAndColor> getAllByProductId(UUID productId);
}
