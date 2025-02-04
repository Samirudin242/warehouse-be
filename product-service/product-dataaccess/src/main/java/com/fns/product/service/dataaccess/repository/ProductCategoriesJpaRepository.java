package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductCategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductCategoriesJpaRepository extends JpaRepository<ProductCategoriesEntity, UUID> {
    List<ProductCategoriesEntity> findByParentId(UUID parentId);
}
