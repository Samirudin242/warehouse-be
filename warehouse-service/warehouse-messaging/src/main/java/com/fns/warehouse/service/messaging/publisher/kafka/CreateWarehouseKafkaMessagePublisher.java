package com.fns.warehouse.service.messaging.publisher.kafka;


import com.fns.kafka.warehouse.avro.model.WarehouseReportingRequestAvroModel;
import com.fns.kafka.producer.KafkaMessageHelper;
import com.fns.kafka.producer.service.KafkaProducer;
import com.fns.warehouse.service.domain.config.WarehouseServiceConfigData;

import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.fns.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedRequestMessagePublisher;
import com.fns.warehouse.service.messaging.mapper.WarehouseMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateWarehouseKafkaMessagePublisher implements WarehouseCreatedRequestMessagePublisher {
    private final WarehouseMessagingDataMapper warehouseMessagingDataMapper;
    private final WarehouseServiceConfigData warehouseServiceConfigData;
    private final KafkaProducer<String, WarehouseReportingRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper warehouseKafkaMessageHelper;

    public CreateWarehouseKafkaMessagePublisher(WarehouseMessagingDataMapper warehouseMessagingDataMapper,
                                                WarehouseServiceConfigData warehouseServiceConfigData,
                                                KafkaProducer<String, WarehouseReportingRequestAvroModel> kafkaProducer,
                                                KafkaMessageHelper warehouseKafkaMessageHelper) {
        this.warehouseMessagingDataMapper = warehouseMessagingDataMapper;
        this.warehouseServiceConfigData = warehouseServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.warehouseKafkaMessageHelper = warehouseKafkaMessageHelper;
    }

    @Override
    public void publish(WarehouseCreatedEvent domainEvent) {
        String warehouseId = domainEvent.getEntity().getId().getValue().toString();
        log.info("Received OrderCreatedEvent for order id: {}", warehouseId);

        try {
            WarehouseReportingRequestAvroModel warehouseReportingRequestAvroModel = warehouseMessagingDataMapper
                    .warehouseCreatedEventToWarehouseReportingRequestAvroModel(domainEvent);

            kafkaProducer.send(warehouseServiceConfigData.getWarehouseReportingRequestTopicName(),
                    warehouseId,
                    warehouseReportingRequestAvroModel,
                    warehouseKafkaMessageHelper
                            .getKafkaCallback(warehouseServiceConfigData.getWarehouseReportingResponseTopicName(),
                                    warehouseReportingRequestAvroModel,
                                    warehouseId,
                                    "WarehouseReportingRequestAvroModel"));

            log.info("WarehouseReportingRequestAvroModel sent to Kafka for order id: {}", warehouseReportingRequestAvroModel.getWarehouseId());
        } catch (Exception e) {
            log.error("Error while sending WarehouseReportingRequestAvroModel message" +
                    " to kafka with order id: {}, error: {}", warehouseId, e.getMessage());
        }

        log.info("Warehouse requested sent to Kafka");
    }
}

