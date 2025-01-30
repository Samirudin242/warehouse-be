package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.domain.entity.ProductReviews;
import com.fns.product.service.domain.ports.output.repository.ProductReviewRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductReviewImpl implements ProductReviewRepository {
    @Override
    public ProductReviews save(ProductReviews product) {
        return null;
    }

    @Override
    public List<ProductReviews> getProductsByProductId(UUID productId) {
        return List.of();
    }
}
