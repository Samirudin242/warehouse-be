package com.fns.warehouse.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.fns.warehouse.service.domain.exception.WarehouseDomainException;

import java.time.ZonedDateTime;
import java.util.UUID;

public class WarehouseDomainServiceImpl implements WarehouseDomainService {
    @Override
    public WarehouseCreatedEvent createWarehouse(UUID id, UUID adminId, UUID locationId, String name, String address, String city, String cityId, String province, String provinceId, String postalCode, DomainEventPublisher<WarehouseCreatedEvent> publisher) throws WarehouseDomainException {
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
                .build();

        return new WarehouseCreatedEvent(warehouse, ZonedDateTime.now(), publisher);
    }
}
