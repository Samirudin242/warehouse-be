package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.ProductResponse;
import com.fns.product.service.domain.dto.create.ProductReviewCommand;
import com.fns.product.service.domain.dto.get.ReviewResponse;
import com.fns.product.service.domain.entity.ProductReview;
import com.fns.product.service.domain.ports.input.service.ReviewApplicationService;
import com.fns.product.service.domain.ports.output.repository.ProductReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Random;
import java.util.UUID;

@Slf4j
@Validated
@Service
public class ReviewApplicationServiceImpl implements ReviewApplicationService {
    private final ProductReviewRepository productReviewRepository;

    public ReviewApplicationServiceImpl( ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    @Override
    public ReviewResponse save(ProductReviewCommand productReviewCommand) {
        return productReviewRepository.save(productReviewCommand);
    }

    @Override
    public Page<ReviewResponse> getReviewByProductId(Integer page, Integer size, UUID productId, Integer rating) {
        return productReviewRepository.getProductsByProductId(page, size, productId, rating);
    }
}
