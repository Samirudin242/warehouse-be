package com.fns.warehouse.service.dataaccess.warehouse.mapper;

import com.fns.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.fns.warehouse.service.dataaccess.warehouse.entity.WarehouseLocationEntity;
import com.fns.domain.valueobject.*;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.valueobject.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

import static com.fns.warehouse.service.domain.entity.Warehouse.FAILURE_MESSAGE_DELIMITER;

@Component
public class WarehouseDataAccessMapper {
    public WarehouseEntity warehouseToWarehouseEntity(Warehouse warehouse) {
        // Create a WarehouseEntity with basic properties
        WarehouseEntity warehouseEntity = WarehouseEntity.builder()
                .id(warehouse.getWarehouseId().getValue())  // Assuming warehouseId is a value object
                .name(warehouse.getName())
                .warehouseStatus(warehouse.getStatus())
                .failureMessages(warehouse.getFailureMessages() != null ?
                        String.join(FAILURE_MESSAGE_DELIMITER, warehouse.getFailureMessages()) : "")
                .build();

        // If location exists, map it to WarehouseLocationEntity and set the relationship
        if (warehouse.getLocation() != null) {
            // Map the location to WarehouseLocationEntity
            WarehouseLocationEntity locationEntity = locationToWarehouseLocationEntity(warehouse.getLocation());

            // Set the relationship: the location belongs to this warehouse
            locationEntity.setWarehouse(warehouseEntity);

            // Set the location entity in the warehouse entity
            warehouseEntity.setWarehouseLocation(locationEntity);
        } else {
            // If no location is provided, set a default location (optional)
            WarehouseLocationEntity defaultLocation = locationToWarehouseLocationEntity(
                    Location.builder().address("Jl. Default").build());
            warehouseEntity.setWarehouseLocation(defaultLocation);
        }

        return warehouseEntity;
    }

    public WarehouseLocationEntity locationToWarehouseLocationEntity(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        return WarehouseLocationEntity.builder()
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .address(location.getAddress())
                .city(location.getCity())
                .province(location.getProvince())
                .district(location.getDistrict())
                .postalCode(location.getPostalCode())
                .build();
    }

    public Location warehouseLocationEntityToLocation(WarehouseLocationEntity warehouseLocationEntity) {
        if (warehouseLocationEntity == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        return Location.builder()
                .latitude(warehouseLocationEntity.getLatitude())
                .longitude(warehouseLocationEntity.getLongitude())
                .address(warehouseLocationEntity.getAddress())
                .city(warehouseLocationEntity.getCity())
                .province(warehouseLocationEntity.getProvince())
                .district(warehouseLocationEntity.getDistrict())
                .postalCode(warehouseLocationEntity.getPostalCode())
                .build();
    }


    public Warehouse warehouseEntityToWarehouse(WarehouseEntity warehouseEntity) {
        return Warehouse.builder()
                .warehouseId(new WarehouseId(warehouseEntity.getId()))
                .name(warehouseEntity.getName())
                .location(warehouseLocationEntityToLocation(warehouseEntity.getWarehouseLocation()))
                .status(warehouseEntity.getWarehouseStatus())
                .failureMessages(warehouseEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
                        new ArrayList<>(Arrays.asList(warehouseEntity.getFailureMessages()
                                .split(FAILURE_MESSAGE_DELIMITER))))
                .build();
    }
}
