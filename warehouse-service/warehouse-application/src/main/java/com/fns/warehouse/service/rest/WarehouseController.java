package com.fns.warehouse.service.rest;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetAllWarehouseResponse;
import com.fns.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/warehouse", produces = "application/vnd.api.v1+json")
public class WarehouseController {

    private final WarehouseApplicationService warehouseApplicationService;

    public WarehouseController(WarehouseApplicationService warehouseApplicationService) {
        this.warehouseApplicationService = warehouseApplicationService;
    }

    @GetMapping
    public ResponseEntity<Page<GetAllWarehouseResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        Page<GetAllWarehouseResponse> response  = warehouseApplicationService.getAllWarehouse(page, size);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateWarehouseResponse> createWarehouse(@RequestBody CreateWarehouseCommand createWarehouseCommand) {
        CreateWarehouseResponse createWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);

        return ResponseEntity.ok(createWarehouseResponse);
    }
}

