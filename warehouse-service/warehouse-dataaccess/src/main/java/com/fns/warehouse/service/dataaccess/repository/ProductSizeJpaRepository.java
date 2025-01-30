package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductSizeJpaRepository extends JpaRepository<ProductSizeEntity, UUID> {
}
