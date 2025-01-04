package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.entity.Warehouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WarehouseCommandHandler {

    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseRepository warehouseRepository;

    public WarehouseCommandHandler(WarehouseDataMapper warehouseDataMapper, WarehouseRepository warehouseRepository) {
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseRepository = warehouseRepository;
    }

    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {

        Warehouse savedWarehouse = warehouseDataMapper.warehouseCommandToWarehouse(createWarehouseCommand);
        savedWarehouse = saveWarehouse(savedWarehouse);

        return warehouseDataMapper.createWarehouseResponse(savedWarehouse);
    }


    private Warehouse saveWarehouse(Warehouse warehouse) {

        try {
            return warehouseRepository.saveWarehouse(warehouse);
           } catch (Exception e) {
            log.error("Failed to save warehouse: {}", warehouse, e);
            throw new RuntimeException(e);  // rethrow the exception to propagate it upwards
        }

    }

}
