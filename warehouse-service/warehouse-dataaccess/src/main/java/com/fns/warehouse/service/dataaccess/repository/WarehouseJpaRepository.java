package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseEntity, UUID> {

    @Query("SELECT w FROM WarehouseEntity w WHERE w.name ILIKE CONCAT('%', :name, '%')")
    Page<WarehouseEntity> findByNameILike(String name, Pageable pageable);

}
