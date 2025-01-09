package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.LocationEntity;
import com.fns.warehouse.service.domain.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LocationJpaRepository extends JpaRepository<LocationEntity, UUID> {
    List<LocationEntity> findByWarehousesId(UUID warehouseId);
}
