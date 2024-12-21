package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductImages;

public interface ProductImageRepository {
    ProductImages saveProductImages(ProductImages productImages);
}
