package com.fns.product.service.domain.rest;

import com.fns.product.service.domain.dto.create.ProductReviewCommand;
import com.fns.product.service.domain.dto.get.ProductResponse;
import com.fns.product.service.domain.dto.get.ReviewResponse;
import com.fns.product.service.domain.ports.input.service.ReviewApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "review", produces = "application/vnd.api.v1+json")
public class ReviewController {

    private final ReviewApplicationService  reviewApplicationService;

    public ReviewController(ReviewApplicationService reviewApplicationService) {
        this.reviewApplicationService = reviewApplicationService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ProductReviewCommand productReviewCommand) {
        ReviewResponse reviewResponse = reviewApplicationService.save(productReviewCommand);
        log.info("Product Review created: {}", reviewResponse);
        return ResponseEntity.ok(reviewResponse);
    }



}
