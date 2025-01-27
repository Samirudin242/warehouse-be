package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.LocationEntity;
import com.fns.product.service.dataaccess.entity.UserEntity;
import com.fns.product.service.dataaccess.entity.WarehouseEntity;
import com.fns.product.service.dataaccess.repository.UserJpaRepository;
import com.fns.product.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataAccessMapper {

    private final UserJpaRepository userJpaRepository;

    public WarehouseDataAccessMapper(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    public WarehouseEntity warehouseToWarehouseEntity(Warehouse warehouse) {

        UserEntity user = userJpaRepository.findById(warehouse.getAdmin_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin_id"));

        return WarehouseEntity.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .status(WarehouseEntity.WarehouseStatus.ACTIVE)
                .user(user)
                .build();
    }

    public LocationEntity locationEntityFromWarehouse(Warehouse warehouse, WarehouseEntity warehouseEntity) {

        return LocationEntity.builder()
                .id(warehouse.getLocation_id())
                .address(warehouse.getAddress())
                .city_id(warehouse.getCity_id())
                .province_id(warehouse.getProvince_id())
                .latitude(warehouse.getLatitude())
                .longitude(warehouse.getLongitude())
                .city(warehouse.getCity())
                .province(warehouse.getProvince())
                .postal_code(warehouse.getPostal_code())
                .warehouses(warehouseEntity)
                .build();
    }
}
