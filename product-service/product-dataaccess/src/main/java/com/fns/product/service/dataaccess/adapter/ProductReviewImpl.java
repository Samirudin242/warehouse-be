package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.UserEntity;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductReviewJpaRepository;
import com.fns.product.service.dataaccess.repository.UserJpaRepository;
import com.fns.product.service.domain.dto.create.ProductReviewCommand;
import com.fns.product.service.domain.dto.get.ReviewResponse;
import com.fns.product.service.domain.entity.ProductReview;
import com.fns.product.service.dataaccess.entity.ProductReviews;
import com.fns.product.service.domain.ports.output.repository.ProductReviewRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductReviewImpl implements ProductReviewRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductReviewJpaRepository productReviewJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public ProductReviewImpl(ProductJpaRepository productJpaRepository, ProductReviewJpaRepository productReviewJpaRepository, UserJpaRepository userJpaRepository) {
        this.productJpaRepository = productJpaRepository;
        this.productReviewJpaRepository = productReviewJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public ReviewResponse save(ProductReviewCommand productReviewCommand) {

        ProductEntity productEntity = productJpaRepository.findById(productReviewCommand.getProduct_id())
                .orElseThrow(() -> new IllegalArgumentException("Product  not found with id: " + productReviewCommand.getProduct_id()));

        UserEntity userEntity = userJpaRepository.findById(productReviewCommand.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + productReviewCommand.getUser_id()));

        ProductReviews productReviews = ProductReviews.builder()
                .comment(productReviewCommand.getComment())
                .rating(productReviewCommand.getRating())
                .product(productEntity)
                .userEntity(userEntity)
                .build();

       ProductReviews saved = productReviewJpaRepository.save(productReviews);

        return ReviewResponse.builder()
                .id(saved.getId())
                .product_id(saved.getProduct().getId())
                .comment(saved.getComment())
                .count(saved.getCount())
                .build();
    }

    @Override
    public List<ProductReview> getProductsByProductId(UUID productId) {
        return List.of();
    }
}
