package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductSizes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductSizeRepository extends JpaRepository<ProductSizes, UUID> {
}
