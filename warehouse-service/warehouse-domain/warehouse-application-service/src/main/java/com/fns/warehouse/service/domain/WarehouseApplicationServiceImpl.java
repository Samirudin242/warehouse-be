package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Validated
@Service
public class WarehouseApplicationServiceImpl implements WarehouseApplicationService {

    private final WarehouseCommandHandler warehouseCommandHandler;

    public WarehouseApplicationServiceImpl(WarehouseCommandHandler warehouseCommandHandler) {
        this.warehouseCommandHandler = warehouseCommandHandler;
    }

    @Override
    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return warehouseCommandHandler.createWarehouse(createWarehouseCommand);
    }
}
