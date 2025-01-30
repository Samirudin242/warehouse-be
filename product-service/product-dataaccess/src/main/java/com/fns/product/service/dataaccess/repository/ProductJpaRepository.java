package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    @Query("SELECT p FROM ProductEntity p WHERE p.name ILIKE CONCAT('%', :name, '%')")
    Page<ProductEntity> findByNameILike(String name, Pageable pageable);
}
