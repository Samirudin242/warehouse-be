package com.fns.reporting.service.messaging.mapper;

import com.fns.kafka.warehouse.avro.model.WarehouseReportingRequestAvroModel;
import com.fns.reporting.service.domain.entity.Report;
import com.fns.reporting.service.domain.event.ReportCreatedEvent;
import org.springframework.stereotype.Component;
import com.fns.reporting.service.domain.dto.message.WarehouseRequest;

import java.util.UUID;

@Component
public class ReportMessagingDataMapper {

    public WarehouseReportingRequestAvroModel reportCreatedEventToWarehouseReportingRequestAvroModel(ReportCreatedEvent reportCreatedEvent) {
        Report report = reportCreatedEvent.getEntity();
        return WarehouseReportingRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId(UUID.randomUUID().toString())
                .setWarehouseId(report.getId().getValue().toString())
                .setCreatedAt(reportCreatedEvent.getCreatedAt().toInstant())
                .build();
    }

    public WarehouseRequest warehouseReportingAvroModelToWarehouseRequest(WarehouseReportingRequestAvroModel
                                                                             warehouseReportingRequestAvroModel) {
        return WarehouseRequest.builder()
                .id(warehouseReportingRequestAvroModel.getId())
                .sagaId(warehouseReportingRequestAvroModel.getSagaId())
                .createdAt(warehouseReportingRequestAvroModel.getCreatedAt())
                .build();
    }

}
