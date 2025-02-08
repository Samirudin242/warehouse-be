package com.fns.product.service.domain.ports.input.service;

import com.fns.product.service.domain.dto.create.ProductResponse;
import com.fns.product.service.domain.dto.create.ProductReviewCommand;
import com.fns.product.service.domain.dto.get.ReviewResponse;
import com.fns.product.service.domain.entity.ProductReview;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.UUID;

public interface ReviewApplicationService {
    ReviewResponse save(@Valid ProductReviewCommand productReviewCommand);

    Page<ReviewResponse> getReviewByProductId(Integer page, Integer size, UUID productId, Integer rating);
}
