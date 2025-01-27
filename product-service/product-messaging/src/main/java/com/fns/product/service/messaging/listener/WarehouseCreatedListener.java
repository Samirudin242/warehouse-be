package com.fns.product.service.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.product.service.domain.dto.message.WarehouseModel;
import com.fns.product.service.domain.ports.input.message.listener.WarehouseMessageListener;
import com.fns.product.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.product.service.messaging.mapper.ProductMessagingMapper;
import com.fns.product.service.messaging.model.WarehouseKafkaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WarehouseCreatedListener {

    private final ObjectMapper objectMapper;
    private final WarehouseRepository warehouseRepository;
    private final ProductMessagingMapper productMessagingMapper;
    private final WarehouseMessageListener warehouseMessageListener;

    public WarehouseCreatedListener(ObjectMapper objectMapper, WarehouseRepository warehouseRepository, ProductMessagingMapper productMessagingMapper, WarehouseMessageListener warehouseMessageListener) {
        this.objectMapper = objectMapper;
        this.warehouseRepository = warehouseRepository;
        this.productMessagingMapper = productMessagingMapper;
        this.warehouseMessageListener = warehouseMessageListener;
    }

    @KafkaListener(topics = "warehouse_created", groupId = "product-group")
    public void listenWarehouse (String message) {
        try {
            log.info("Received warehouse_created event: {}", message);
            WarehouseKafkaModel warehouseKafkaModel = objectMapper.readValue(message, WarehouseKafkaModel.class);

            log.info("warehouse_created event kafka model: {}", warehouseKafkaModel);

            WarehouseModel warehouse = productMessagingMapper.mapToWarehouseModel(warehouseKafkaModel);

            warehouseMessageListener.savedWarehouse(warehouse);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
