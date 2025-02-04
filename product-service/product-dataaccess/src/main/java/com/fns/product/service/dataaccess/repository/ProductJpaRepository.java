package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    @Query("SELECT p FROM ProductEntity p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND (COALESCE(:categoryIds, NULL) IS NULL OR p.category.id IN :categoryIds)")
    Page<ProductEntity> findByNameAndCategoryIds(
            @Param("name") String name,
            @Param("categoryIds") List<UUID> categoryIds,
            Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE " +
            "(:categoryIds IS NULL OR p.category.id IN :categoryIds)")
    Page<ProductEntity> findByCategoryIds(
            @Param("categoryIds") List<UUID> categoryIds,
            Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<ProductEntity> findByNameILike(@Param("name") String name, Pageable pageable);
}