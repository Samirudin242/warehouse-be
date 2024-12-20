package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

}
