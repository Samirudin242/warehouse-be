package com.fns.warehouse.service.messaging.publisher.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.fns.warehouse.service.domain.ports.output.message.WarehouseMessagePublisher;
import com.fns.warehouse.service.messaging.mapper.WarehouseMessagingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateWarehouseKafkaPublisher implements WarehouseMessagePublisher {

    private final WarehouseMessagingMapper warehouseMessagingMapper;
    private static final String TOPIC = "warehouse_created";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    public CreateWarehouseKafkaPublisher(WarehouseMessagingMapper warehouseMessagingMapper, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.warehouseMessagingMapper = warehouseMessagingMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(WarehouseCreatedEvent domainEvent) {
        try {
            log.info("Publishing {}", domainEvent.getEntity());
            String message = objectMapper.writeValueAsString(
                    warehouseMessagingMapper.convertToWarehouse(domainEvent.getEntity())
            );
            kafkaTemplate.send(TOPIC, message);

            log.info("Published warehouse_created event to Kafka topic {}: {}", TOPIC, message);
        } catch (Exception e) {
            log.error("Error serializing warehouse event for Kafka: {}", domainEvent, e);
            throw new RuntimeException("Failed to serialize warehouse event", e);
        }
    }


}
