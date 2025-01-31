package com.fns.warehouse.service.messaging.publisher.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.warehouse.service.domain.event.StockUpdateEvent;
import com.fns.warehouse.service.domain.ports.output.message.StockUpdateMessagePublisher;
import com.fns.warehouse.service.messaging.mapper.WarehouseMessagingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateStockProductPublisher implements StockUpdateMessagePublisher {
    private final WarehouseMessagingMapper warehouseMessagingMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = "stock-updated";

    public UpdateStockProductPublisher(WarehouseMessagingMapper warehouseMessagingMapper, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.warehouseMessagingMapper = warehouseMessagingMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(StockUpdateEvent domainEvent) {
        try {
            log.info("Publishing {}", domainEvent.getEntity());
            String message = objectMapper.writeValueAsString(
                    warehouseMessagingMapper.convertToStockKafkaModel(domainEvent.getEntity())
            );
            kafkaTemplate.send(TOPIC, message);
        } catch (Exception e) {
            log.error("Error serializing stock update event for Kafka: {}", domainEvent, e);
            throw new RuntimeException("Failed to serialize stock update event", e);
        }
    }
}
