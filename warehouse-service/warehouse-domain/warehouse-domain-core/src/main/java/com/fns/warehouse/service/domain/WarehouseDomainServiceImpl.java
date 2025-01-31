package com.fns.warehouse.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.event.StockUpdateEvent;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.fns.warehouse.service.domain.exception.WarehouseDomainException;
import com.fns.warehouse.service.domain.entity.Stock;

import java.time.ZonedDateTime;
import java.util.UUID;

public class WarehouseDomainServiceImpl implements WarehouseDomainService {

    @Override
    public WarehouseCreatedEvent createWarehouse(UUID id, UUID adminId, UUID locationId, String name, String address, String city, String cityId, String province, String provinceId, String postalCode, Double latitude, Double longitude, DomainEventPublisher<WarehouseCreatedEvent> publisher) throws WarehouseDomainException {
        Warehouse warehouse = Warehouse.builder()
                .id(id)
                .admin_id(adminId)
                .location_id(locationId)
                .name(name)
                .address(address)
                .city(city)
                .city_id(cityId)
                .province(province)
                .province_id(provinceId)
                .postal_code(postalCode)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        return new WarehouseCreatedEvent(warehouse, ZonedDateTime.now(), publisher);
    }

    @Override
    public StockUpdateEvent stockUpdateEvent(UUID id, int quantity, UUID productId, UUID warehouseId, DomainEventPublisher<StockUpdateEvent> publisher) throws WarehouseDomainException {
        com.fns.warehouse.service.domain.entity.Stock stock = Stock.builder()
                .id(id)
                .quantity(quantity)
                .product_id(productId)
                .warehouse_id(warehouseId)
                .build();

        return new StockUpdateEvent(stock, ZonedDateTime.now(), publisher);
    }
}
