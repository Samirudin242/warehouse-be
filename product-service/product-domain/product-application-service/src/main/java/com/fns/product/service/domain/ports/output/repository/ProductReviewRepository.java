package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.dto.create.ProductReviewCommand;
import com.fns.product.service.domain.dto.get.ReviewResponse;
import com.fns.product.service.domain.entity.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ProductReviewRepository {

    ReviewResponse save (ProductReviewCommand productReviewCommand);

    Page<ReviewResponse> getProductsByProductId (Integer page, Integer size, UUID productId, Integer rating);

}
