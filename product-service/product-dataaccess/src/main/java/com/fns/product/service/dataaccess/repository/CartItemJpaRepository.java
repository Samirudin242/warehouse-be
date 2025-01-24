package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, UUID> {
    Optional<CartItemEntity> findByCart_IdAndProduct_Id(UUID cartId, UUID productId);
}
