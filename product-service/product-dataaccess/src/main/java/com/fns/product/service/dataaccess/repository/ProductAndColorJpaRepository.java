package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductAndColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductAndColorJpaRepository extends JpaRepository<ProductAndColorEntity, UUID> {

    List<ProductAndColorEntity> findByProduct_Id(UUID productId);

}
