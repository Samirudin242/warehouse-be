package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductCategories;

import java.util.Optional;
import java.util.UUID;

public interface ProductCategoriesRepository {
    Optional<ProductCategories> findById(UUID categoryId);
}