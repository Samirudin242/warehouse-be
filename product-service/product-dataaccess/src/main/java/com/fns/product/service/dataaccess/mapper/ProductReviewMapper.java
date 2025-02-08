package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductReviews;
import com.fns.product.service.domain.dto.get.ReviewResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewMapper {

    public ReviewResponse responseFromEntity(ProductReviews productReviews) {
        return ReviewResponse.builder()
                .id(productReviews.getId())
                .product_id(productReviews.getProduct().getId())
                .comment(productReviews.getComment())
                .count(productReviews.getCount())
                .rating(productReviews.getRating())
                .createdAt(productReviews.getCreatedAt())
                .build();
    }
}
