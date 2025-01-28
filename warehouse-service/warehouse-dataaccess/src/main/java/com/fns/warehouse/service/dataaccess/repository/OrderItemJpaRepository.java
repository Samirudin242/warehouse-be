package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, UUID> {
}
