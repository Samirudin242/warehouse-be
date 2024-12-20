package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductColors;

import java.util.Optional;
import java.util.UUID;

public interface ProductColorsRepository {
    Optional<ProductColors> findById(UUID colorId);
}
