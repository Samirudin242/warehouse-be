package com.fns.warehouse.service.domain.mapper;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetNearestWarehouseResponse;
import com.fns.warehouse.service.domain.entity.Location;
import com.fns.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataMapper {
    public  Warehouse warehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return Warehouse.builder()
                .name(createWarehouseCommand.getName())
                .admin_id(createWarehouseCommand.getAdmin_id())
                .address(createWarehouseCommand.getAddress())
                .city(createWarehouseCommand.getCity())
                .city_id(createWarehouseCommand.getCity_id())
                .province(createWarehouseCommand.getProvince())
                .province_id(createWarehouseCommand.getProvince_id())
                .latitude(createWarehouseCommand.getLatitude())
                .longitude(createWarehouseCommand.getLongitude())
                .postal_code(createWarehouseCommand.getPostal_code())
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
                .latitude(createWarehouseCommand.getLatitude())
                .longitude(createWarehouseCommand.getLongitude())
                .build();
    }

    public GetNearestWarehouseResponse getNearestWarehouseResponse(Warehouse warehouse) {
        return GetNearestWarehouseResponse.builder()
               .id(warehouse.getId())
               .name(warehouse.getName())
               .address(warehouse.getAddress())
                .province_id(warehouse.getProvince_id())
                .city_id(warehouse.getCity_id())
                .latitude(warehouse.getLatitude())
                .longitude(warehouse.getLongitude())
               .build();
    }

}
