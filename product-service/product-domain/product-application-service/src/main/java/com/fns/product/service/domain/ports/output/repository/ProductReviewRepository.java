package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.ProductReviews;

import java.util.List;
import java.util.UUID;

public interface ProductReviewRepository {

    ProductReviews save (ProductReviews product);

    List<ProductReviews> getProductsByProductId (UUID productId);

}
