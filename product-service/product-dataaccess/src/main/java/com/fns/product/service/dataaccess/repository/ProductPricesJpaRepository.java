package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductPricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductPricesJpaRepository extends JpaRepository<ProductPricesEntity, UUID> {
    ProductPricesEntity findByProduct_Id(UUID productId);
}
