package com.fns.warehouse.service.domain.mapper;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.entity.Location;
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

    public Location commandToLocation(CreateWarehouseCommand createWarehouseCommand, Warehouse warehouse) {
        return Location.builder()
                .address(createWarehouseCommand.getAddress())
                .city_id(createWarehouseCommand.getCity_id())
                .province_id(createWarehouseCommand.getProvince_id())
                .warehouse_id(warehouse.getId())
                .city(createWarehouseCommand.getCity())
                .province(createWarehouseCommand.getProvince())
                .postal_code(createWarehouseCommand.getPostal_code())
                .build();
    }

}
