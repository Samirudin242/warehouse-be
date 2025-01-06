package com.fns.warehouse.service.dataaccess.mapper;

import com.fns.warehouse.service.dataaccess.entity.LocationEntity;
import com.fns.warehouse.service.dataaccess.entity.WarehouseEntity;
import com.fns.warehouse.service.dataaccess.repository.WarehouseJpaRepository;
import com.fns.warehouse.service.domain.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocationDataAccessMapper {

    @Autowired
    private WarehouseJpaRepository warehouseJpaRepository;


    public LocationEntity locationToLocationEntity(Location location) {

        WarehouseEntity warehouse = getWarehouseById(location.getWarehouse_id());

        return  LocationEntity.builder()
                .address(location.getAddress())
                .province(location.getProvince())
                .city(location.getCity())
                .province_id(location.getProvince_id())
                .city_id(location.getCity_id())
                .postal_code(location.getPostal_code())
                .warehouses(warehouse)
                .build();
    }

    public Location locationEntityToLocation(LocationEntity locationEntity) {
        return Location.builder()
                .id(locationEntity.getId())
               .address(locationEntity.getAddress())
               .city_id(locationEntity.getCity_id())
               .province_id(locationEntity.getProvince_id())
               .warehouse_id(locationEntity.getWarehouses().getId())
               .city(locationEntity.getCity())
               .province(locationEntity.getProvince())
                .postal_code(locationEntity.getPostal_code())
               .build();
    }


    private WarehouseEntity getWarehouseById(UUID id) {
        return warehouseJpaRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Warehouse not found with id: " + id));
    }


}
