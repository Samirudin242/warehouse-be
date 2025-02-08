package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetAllWarehouseResponse;
import com.fns.warehouse.service.domain.entity.Location;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.output.message.WarehouseMessagePublisher;
import com.fns.warehouse.service.domain.ports.output.repository.LocationRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.entity.Warehouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class WarehouseCommandHandler {

    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseRepository warehouseRepository;
    private final LocationRepository locationRepository;
    private final WarehouseMessagePublisher warehouseMessagePublisher;
    private final WarehouseDomainService warehouseDomainService;
    public WarehouseCommandHandler(WarehouseDataMapper warehouseDataMapper, WarehouseRepository warehouseRepository, LocationRepository locationRepository, WarehouseMessagePublisher warehouseMessagePublisher, WarehouseDomainService warehouseDomainService) {
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseRepository = warehouseRepository;
        this.locationRepository = locationRepository;
        this.warehouseMessagePublisher = warehouseMessagePublisher;
        this.warehouseDomainService = warehouseDomainService;
    }

    @Transactional
    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {

        Warehouse savedWarehouse = warehouseDataMapper.warehouseCommandToWarehouse(createWarehouseCommand);

        Warehouse warehouse = saveWarehouse(savedWarehouse);

        log.info("saved warehouse: {}", warehouse);


        Location savedLocation = warehouseDataMapper.commandToLocation(createWarehouseCommand, warehouse);

        Location location = saveLocation(savedLocation);


        WarehouseCreatedEvent warehouseEvent = warehouseDomainService.createWarehouse(
                warehouse.getId(),
                createWarehouseCommand.getAdmin_id(),
                location.getId(),
                warehouse.getName(),
                location.getAddress(),
                location.getCity(),
                location.getCity_id(),
                location.getProvince(),
                location.getProvince_id(),
                location.getPostal_code(),
                location.getLatitude(),
                location.getLongitude(),
                warehouseMessagePublisher
        );

        warehouseMessagePublisher.publish(warehouseEvent);

        return warehouseDataMapper.createWarehouseResponse(savedWarehouse);
    }

    public Page<GetAllWarehouseResponse> getAllWarehouse(Integer page, Integer pageSize, String name) {
        return warehouseRepository.getAllWarehouse(page, pageSize, name);
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
            return locationRepository.savedLocation(location);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
