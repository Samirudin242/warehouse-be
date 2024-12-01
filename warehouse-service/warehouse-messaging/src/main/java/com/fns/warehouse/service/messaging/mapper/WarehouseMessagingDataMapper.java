package com.fns.warehouse.service.messaging.mapper;

import com.fns.kafka.warehouse.avro.model.WarehouseReportingRequestAvroModel;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehouseMessagingDataMapper {

    public WarehouseReportingRequestAvroModel warehouseCreatedEventToWarehouseReportingRequestAvroModel(WarehouseCreatedEvent warehouseCreatedEvent) {
        Warehouse warehouse = warehouseCreatedEvent.getEntity();
        return WarehouseReportingRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId(UUID.randomUUID().toString())
                .setWarehouseId(warehouse.getId().getValue().toString())
                .setCreatedAt(warehouseCreatedEvent.getCreatedAt().toInstant())
                .build();
    }


}
