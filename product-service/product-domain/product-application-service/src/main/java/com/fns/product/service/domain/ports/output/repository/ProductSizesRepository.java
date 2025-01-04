package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductSizes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductSizesRepository {
    Optional<ProductSizes> findById(UUID sizeId);

    List<ProductSizes> findAllProductSizes();
}
