package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.StockMutationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockMutationJpaRepository extends JpaRepository<StockMutationEntity, UUID> {
}
