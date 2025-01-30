package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.MutationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockMutationTypeJpaRepository extends JpaRepository<MutationTypeEntity, UUID> {
}

