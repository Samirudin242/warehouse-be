package com.fns.warehouse.service.domain;

import com.fns.domain.valueobject.WarehouseId;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.create.WarehouseLocation;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.exception.WarehouseDomainException;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import com.fns.warehouse.service.domain.ports.output.repository.StockRepository;
import com.fns.warehouse.service.domain.ports.output.repository.UserRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.valueobject.WarehouseStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = WarehouseTestConfiguration.class)
@ActiveProfiles("test")
public class WarehouseApplicationServiceTest {

    @Autowired
    private WarehouseApplicationService warehouseApplicationService;

    @Autowired
    private WarehouseDataMapper warehouseDataMapper;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository;

    private CreateWarehouseCommand createWarehouseCommand;
    private CreateWarehouseCommand createWarehouseCommandLocationNotCompleted;
    private final UUID WAREHOUSE_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");

    @BeforeAll
    public void init() {
        createWarehouseCommand = CreateWarehouseCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .name("Sinar Jaya")
                .location(WarehouseLocation.builder()
                        .latitude(56.90f)
                        .longitude(103.90f)
                        .postalCode("100AB")
                        .city("Paris")
                        .district("Sinai")
                        .province("France")
                        .address("Jl. 123")
                        .build())
                .build();

        createWarehouseCommandLocationNotCompleted = CreateWarehouseCommand.builder()
                .name("sinar Jaya")
                .build();

        Warehouse warehouse = warehouseDataMapper.createWarehouseCommandToWarehouse(createWarehouseCommand);
        warehouse.setId(new WarehouseId(WAREHOUSE_ID));

        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);
    }

    @Test
    public void testCreateWarehouse() {
        CreateWarehouseResponse createWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        assertEquals(WarehouseStatus.ACTIVE, createWarehouseResponse.getWarehouseStatus());
        assertEquals("Warehouse created successfully", createWarehouseResponse.getMessage());
    }

    @Test
    public void testCreateWarehouseWithNullLocation() {
        WarehouseDomainException exception = assertThrows(WarehouseDomainException.class,
                () -> warehouseApplicationService.createWarehouse(createWarehouseCommandLocationNotCompleted));
        assertEquals("Location must be set.", exception.getMessage());
    }
}
