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
                .status(WarehouseEntity.WarehouseStatus.ACTIVE)
                .build();
    }

    public Warehouse warehouseEntityToWarehouse(WarehouseEntity warehouseEntity) {

        List<LocationEntity> locationEntities = locationJpaRepository.findByWarehousesId(warehouseEntity.getId());

        Double latitude = null;
        Double longitude = null;
        String city_id = null;
        String province_id = null;

        if (!locationEntities.isEmpty()) {
            latitude = locationEntities.get(0).getLatitude();
            longitude = locationEntities.get(0).getLongitude();
            city_id = locationEntities.get(0).getCity_id();
            province_id = locationEntities.get(0).getProvince_id();
        }
        // Map the WarehouseEntity to Warehouse
        return Warehouse.builder()
                .id(warehouseEntity.getId())
                .name(warehouseEntity.getName())
                .address(locationEntities.get(0).getAddress())
                .latitude(latitude)
                .longitude(longitude)
                .city_id(city_id)
                .province_id(province_id)
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

    public List<Warehouse> warehouseList(List<WarehouseEntity> warehouseEntities) {
        return warehouseEntities.stream()
               .map(this::warehouseEntityToWarehouse)
               .collect(Collectors.toList());
    }

}
