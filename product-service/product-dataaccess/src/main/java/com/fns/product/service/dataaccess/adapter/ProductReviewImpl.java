package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.UserEntity;
import com.fns.product.service.dataaccess.mapper.ProductReviewMapper;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductReviewJpaRepository;
import com.fns.product.service.dataaccess.repository.UserJpaRepository;
import com.fns.product.service.domain.dto.create.ProductReviewCommand;
import com.fns.product.service.domain.dto.get.ReviewResponse;
import com.fns.product.service.domain.entity.ProductReview;
import com.fns.product.service.dataaccess.entity.ProductReviews;
import com.fns.product.service.domain.ports.output.repository.ProductReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class ProductReviewImpl implements ProductReviewRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductReviewJpaRepository productReviewJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ProductReviewMapper productReviewMapper;

    public ProductReviewImpl(ProductJpaRepository productJpaRepository, ProductReviewJpaRepository productReviewJpaRepository, UserJpaRepository userJpaRepository, ProductReviewMapper productReviewMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productReviewJpaRepository = productReviewJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.productReviewMapper = productReviewMapper;
    }

    @Override
    @Transactional
    public ReviewResponse save(ProductReviewCommand productReviewCommand) {
        ProductEntity productEntity = productJpaRepository.findById(productReviewCommand.getProduct_id())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productReviewCommand.getProduct_id()));

        UserEntity userEntity = userJpaRepository.findById(productReviewCommand.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + productReviewCommand.getUser_id()));

        ProductReviews productReviews = ProductReviews.builder()
                .comment(productReviewCommand.getComment())
                .rating(productReviewCommand.getRating())
                .product(productEntity)
                .userEntity(userEntity)
                .build();

        ProductReviews savedReview = productReviewJpaRepository.save(productReviews);

        Optional<Integer> currentAverage = productReviewJpaRepository.findAverageRatingByProductId(productEntity.getId());

        int newAverageRating = currentAverage.orElse(0);

        productEntity.setRating(newAverageRating);
        productJpaRepository.save(productEntity);

        return ReviewResponse.builder()
                .id(savedReview.getId())
                .product_id(savedReview.getProduct().getId())
                .comment(savedReview.getComment())
                .count(savedReview.getCount())
                .build();
    }

    @Override
    public Page<ReviewResponse> getProductsByProductId(Integer page, Integer size, UUID productId, Integer rating) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ProductReviews> reviewsEntity;

        if(productId != null) {
            reviewsEntity = productReviewJpaRepository.findByProduct_IdOrderByCreatedAtDesc(productId, pageable);
        } else if(rating != null) {
            reviewsEntity = productReviewJpaRepository.findAllFiveStarReviews(pageable, rating);
        } else {
            reviewsEntity = productReviewJpaRepository.findAllByOrderByCreatedAtDesc(pageable);
        }

        List<ReviewResponse> reviews = reviewsEntity.stream().map(productReviewMapper::responseFromEntity)
                .toList();

        return new PageImpl<>(reviews, pageable, reviewsEntity.getTotalElements());
    }

}
