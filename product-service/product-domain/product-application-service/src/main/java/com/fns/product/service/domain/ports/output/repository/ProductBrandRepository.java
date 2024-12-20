package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductBrand;

import java.util.Optional;
import java.util.UUID;

public interface ProductBrandRepository {
    Optional<ProductBrand> findById(UUID brandId); // Return Optional<ProductBrand> instead of ProductBrand
}
