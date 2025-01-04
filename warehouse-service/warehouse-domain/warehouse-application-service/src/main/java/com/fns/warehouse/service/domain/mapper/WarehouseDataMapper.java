package com.fns.warehouse.service.domain.mapper;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataMapper {
    public  Warehouse warehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return Warehouse.builder()
                .name(createWarehouseCommand.getName())
                .admin_id(createWarehouseCommand.getAdmin_id())
                .build();
    }

    public CreateWarehouseResponse createWarehouseResponse(Warehouse warehouse) {
        return CreateWarehouseResponse.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .admin_id(warehouse.getAdmin_id())
                .build();
    }

}
