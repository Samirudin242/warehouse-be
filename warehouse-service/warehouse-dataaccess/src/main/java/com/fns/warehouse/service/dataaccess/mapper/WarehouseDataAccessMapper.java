package com.fns.warehouse.service.dataaccess.mapper;

import com.fns.warehouse.service.dataaccess.entity.WarehouseEntity;

import com.fns.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataAccessMapper {

    public WarehouseEntity warehouseToWarehouseEntity(Warehouse warehouse) {
        // Map the Warehouse to WarehouseEntity
        return WarehouseEntity.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .build();
    }

    public Warehouse warehouseEntityToWarehouse(WarehouseEntity warehouseEntity) {
        // Map the WarehouseEntity to Warehouse
        return Warehouse.builder()
                .id(warehouseEntity.getId())
                .name(warehouseEntity.getName())
                .build();
    }

}
