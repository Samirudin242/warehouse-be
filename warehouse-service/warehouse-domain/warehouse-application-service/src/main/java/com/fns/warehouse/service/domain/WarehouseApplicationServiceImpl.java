package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.NearestWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetAllWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetNearestWarehouseResponse;
import com.fns.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Validated
@Service
public class WarehouseApplicationServiceImpl implements WarehouseApplicationService {

    private final WarehouseCommandHandler warehouseCommandHandler;
    private final OrderCommandHandler orderCommandHandler;
    public WarehouseApplicationServiceImpl(WarehouseCommandHandler warehouseCommandHandler, OrderCommandHandler orderCommandHandler) {
        this.warehouseCommandHandler = warehouseCommandHandler;
        this.orderCommandHandler = orderCommandHandler;
    }

    @Override
    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return warehouseCommandHandler.createWarehouse(createWarehouseCommand);
    }

    @Override
    public Page<GetAllWarehouseResponse> getAllWarehouse(Integer page, Integer pageSize, String name) {
        return warehouseCommandHandler.getAllWarehouse(page, pageSize, name);
    }

    @Override
    public GetNearestWarehouseResponse getNearestWarehouse(NearestWarehouseCommand warehouseCommand) {
        return orderCommandHandler.getNearestWarehouse(warehouseCommand);
    }
}
