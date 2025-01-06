package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.entity.Location;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.output.repository.LocationRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.entity.Warehouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class WarehouseCommandHandler {

    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseRepository warehouseRepository;
    private final LocationRepository locationRepository;

    public WarehouseCommandHandler(WarehouseDataMapper warehouseDataMapper, WarehouseRepository warehouseRepository, LocationRepository locationRepository) {
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseRepository = warehouseRepository;
        this.locationRepository = locationRepository;
    }

    @Transactional
    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {

        Warehouse savedWarehouse = warehouseDataMapper.warehouseCommandToWarehouse(createWarehouseCommand);

        Warehouse warehouse = saveWarehouse(savedWarehouse);

        Location savedLocation = warehouseDataMapper.commandToLocation(createWarehouseCommand, warehouse);

        saveLocation(savedLocation);

        return warehouseDataMapper.createWarehouseResponse(savedWarehouse);
    }


    private Warehouse saveWarehouse(Warehouse warehouse) {

        try {
            return warehouseRepository.saveWarehouse(warehouse);
           } catch (Exception e) {
            log.error("Failed to save warehouse: {}", warehouse, e);
            throw new RuntimeException(e);
        }
    }

    private Location saveLocation(Location location) {
        try {
            return locationRepository.savedLocaion(location);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
