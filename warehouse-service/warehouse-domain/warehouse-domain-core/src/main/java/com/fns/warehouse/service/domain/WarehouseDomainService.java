package com.fns.warehouse.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.event.StockUpdateEvent;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.fns.warehouse.service.domain.exception.WarehouseDomainException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface WarehouseDomainService {
    WarehouseCreatedEvent createWarehouse(
            UUID id,
            UUID adminId,
            UUID locationId,
            String name,
            String address,
            String city,
            String cityId,
            String province,
            String provinceId,
            String postalCode,
            Double latitude,
            Double longitude,
            DomainEventPublisher<WarehouseCreatedEvent> publisher
    ) throws WarehouseDomainException;

    StockUpdateEvent stockUpdateEvent(
            UUID id,
            int quantity,
            UUID productId,
            UUID warehouseId,
            DomainEventPublisher<StockUpdateEvent> publisher
    ) throws WarehouseDomainException;
}
