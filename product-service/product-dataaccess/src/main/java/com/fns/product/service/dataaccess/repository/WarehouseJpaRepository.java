package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseEntity, UUID> {
}
