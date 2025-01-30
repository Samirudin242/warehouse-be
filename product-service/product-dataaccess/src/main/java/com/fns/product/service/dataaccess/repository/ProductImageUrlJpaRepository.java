package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductImageUrlJpaRepository extends JpaRepository<ProductImagesEntity, UUID> {
    @Query("SELECT p FROM ProductImagesEntity p WHERE p.product.id = :productId")
    List<ProductImagesEntity> findByProductId(@Param("productId") UUID productId);

}
