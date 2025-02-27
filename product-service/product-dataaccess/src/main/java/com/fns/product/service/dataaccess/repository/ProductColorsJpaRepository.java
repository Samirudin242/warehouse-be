package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductColorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductColorsJpaRepository extends JpaRepository<ProductColorsEntity, UUID> {
}
