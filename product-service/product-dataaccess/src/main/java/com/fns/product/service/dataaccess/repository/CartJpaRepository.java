package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {
    Optional<CartEntity> findByUsersId(UUID userId);
}