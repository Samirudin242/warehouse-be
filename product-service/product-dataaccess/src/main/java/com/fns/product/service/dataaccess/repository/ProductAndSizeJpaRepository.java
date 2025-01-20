package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductAndSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductAndSizeJpaRepository extends JpaRepository<ProductAndSizeEntity, UUID> {

    List<ProductAndSizeEntity> findByProduct_Id(UUID productId);

}
