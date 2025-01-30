package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductBrand;
import com.fns.product.service.domain.entity.ProductColors;
import com.fns.product.service.domain.entity.ProductSizes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductSizesColorBrandRepository {
    Optional<ProductSizes> findById(UUID sizeId);

    List<ProductSizes> findAllProductSizes();

    List<ProductBrand> findAllBrand();

    List<ProductColors> findAllColors();
}
