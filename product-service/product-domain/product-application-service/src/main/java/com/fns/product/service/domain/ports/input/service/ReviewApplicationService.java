package com.fns.product.service.domain.ports.input.service;

import com.fns.product.service.domain.dto.create.ProductResponse;
import com.fns.product.service.domain.dto.create.ProductReviewCommand;
import com.fns.product.service.domain.dto.get.ReviewResponse;
import com.fns.product.service.domain.entity.ProductReview;

import javax.validation.Valid;

public interface ReviewApplicationService {
    ReviewResponse save(@Valid ProductReviewCommand productReviewCommand);
}
