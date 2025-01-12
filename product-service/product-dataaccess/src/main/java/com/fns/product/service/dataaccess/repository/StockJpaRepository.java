package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockJpaRepository extends JpaRepository<StockEntity, UUID> {
}
