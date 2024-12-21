package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductSizesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductSizeJpaRepository extends JpaRepository<ProductSizesEntity, UUID> {
}
