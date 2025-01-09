package com.fns.warehouse.service.dataaccess.mapper;

import com.fns.warehouse.service.dataaccess.entity.LocationEntity;
import com.fns.warehouse.service.dataaccess.entity.WarehouseEntity;

import com.fns.warehouse.service.dataaccess.repository.LocationJpaRepository;
import com.fns.warehouse.service.domain.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class WarehouseDataAccessMapper {

    @Autowired
    private LocationJpaRepository locationJpaRepository;

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


    public Warehouse warehouseEntityToWarehouseGet(WarehouseEntity warehouseEntity) {

        LocationEntity locationEntity = getLocationsByUserId(warehouseEntity.getId());


        // Map the WarehouseEntity to Warehouse
        return Warehouse.builder()
                .id(warehouseEntity.getId())
                .name(warehouseEntity.getName())
                .address(locationEntity.getAddress())
                .province(locationEntity.getProvince())
                .city(locationEntity.getCity())
                .province_id(locationEntity.getProvince_id())
                .city_id(locationEntity.getCity_id())
                .build();
    }

    public List<Warehouse> warehouseEntityToWarehouse(List<WarehouseEntity> warehouseEntities) {
        return warehouseEntities.stream()
                .map(this::warehouseEntityToWarehouseGet)
                .collect(Collectors.toList());
    }

    public LocationEntity getLocationsByUserId(UUID warehouseId) {
        List<LocationEntity> locations = locationJpaRepository.findByWarehousesId(warehouseId);
        if (!locations.isEmpty()) {
            return locations.get(0);
        } else {
            return null;
        }
    }

}
