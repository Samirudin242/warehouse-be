package com.fns.warehouse.service.rest;

import com.fns.warehouse.service.domain.dto.create.CreateOrderCommand;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.NearestWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetNearestWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.response.CreateOrderResponse;
import com.fns.warehouse.service.domain.ports.input.service.OrderApplicationService;
import com.fns.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/order", produces = "application/vnd.api.v1+json")
public class OrderController {

    private final WarehouseApplicationService warehouseApplicationService;
    private final OrderApplicationService orderApplicationService;

    public OrderController(WarehouseApplicationService warehouseApplicationService, OrderApplicationService orderApplicationService) {
        this.warehouseApplicationService = warehouseApplicationService;
        this.orderApplicationService = orderApplicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GetOrderResponse>> getAllOrder(@PathVariable UUID id)  {
        List<GetOrderResponse> getOrderResponses = orderApplicationService.getAllOrderUser(id);

        return ResponseEntity.ok(getOrderResponses);
    }

    @PostMapping("/nearest-warehouse")
    public ResponseEntity<GetNearestWarehouseResponse> createWarehouse(@RequestBody NearestWarehouseCommand nearestWarehouseCommand) {
        GetNearestWarehouseResponse nearestWarehouseResponse = warehouseApplicationService.getNearestWarehouse(nearestWarehouseCommand);

        return ResponseEntity.ok(nearestWarehouseResponse);
    }

    @PostMapping("/upload-payment/{id}")
    public ResponseEntity<String> uploadProductPhoto(
            @PathVariable UUID id,
            @RequestParam("file") MultipartFile file) {
        try {
            // Upload the photo and get the URL
            String photoUrl = orderApplicationService.uploadPayment(id, file);
            log.info("payment uploaded successfully for Product ID {}: {}", id, photoUrl);
            return ResponseEntity.ok(photoUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload payment: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        log.info("Order created: {}", createOrderCommand);
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);

        return ResponseEntity.ok(createOrderResponse);
    }





}
