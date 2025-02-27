package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductBrandJpaRepository extends JpaRepository<ProductBrandEntity, UUID> {
}
