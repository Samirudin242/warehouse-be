package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.WarehouseEntity;
import com.fns.product.service.domain.entity.Warehouse;
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
}
