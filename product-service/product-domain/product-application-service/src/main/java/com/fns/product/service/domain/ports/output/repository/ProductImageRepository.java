package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductImages;

import java.util.List;
import java.util.UUID;

public interface ProductImageRepository {
    ProductImages saveProductImages(ProductImages productImages);

    List<ProductImages> findByProductId(UUID productId);
}
