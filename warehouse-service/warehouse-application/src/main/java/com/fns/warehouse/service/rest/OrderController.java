package com.fns.warehouse.service.rest;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.NearestWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetNearestWarehouseResponse;
import com.fns.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/order", produces = "application/vnd.api.v1+json")
public class OrderController {

    private final WarehouseApplicationService warehouseApplicationService;

    public OrderController(WarehouseApplicationService warehouseApplicationService) {
        this.warehouseApplicationService = warehouseApplicationService;
    }

    @PostMapping("/nearest-warehouse")
    public ResponseEntity<GetNearestWarehouseResponse> createWarehouse(@RequestBody NearestWarehouseCommand nearestWarehouseCommand) {
        GetNearestWarehouseResponse nearestWarehouseResponse = warehouseApplicationService.getNearestWarehouse(nearestWarehouseCommand);

        return ResponseEntity.ok(nearestWarehouseResponse);
    }
}
