package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.OrderEntity;
import com.fns.warehouse.service.domain.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

    List<OrderEntity> findByUser_IdOrderByOrderDateDesc(UUID userId);

    Page<OrderEntity> findByStatusOrderByOrderDateDesc(OrderStatus status, Pageable pageable);

    Page<OrderEntity> findAllByOrderByOrderDateDesc(Pageable pageable);
}
