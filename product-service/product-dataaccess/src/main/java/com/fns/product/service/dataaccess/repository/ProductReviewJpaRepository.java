package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductReviewJpaRepository extends JpaRepository<ProductReviews, UUID> {
    List<ProductReviews> findByProduct_IdOrderByCreatedAtDesc(UUID productId);

    @Query("SELECT AVG(pr.rating) FROM ProductReviews pr WHERE pr.product.id = :productId")
    Optional<Integer> findAverageRatingByProductId(@Param("productId") UUID productId);
}