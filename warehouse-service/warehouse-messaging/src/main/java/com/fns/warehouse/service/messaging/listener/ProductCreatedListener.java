package com.fns.warehouse.service.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.warehouse.service.domain.dto.message.ProductModel;
import com.fns.warehouse.service.domain.dto.message.UserModel;
import com.fns.warehouse.service.domain.ports.input.message.ProductMessageListener;
import com.fns.warehouse.service.messaging.mapper.ProductMessagingMapper;
import com.fns.warehouse.service.messaging.mapper.WarehouseMessagingMapper;
import com.fns.warehouse.service.messaging.model.ProductKafkaModel;
import com.fns.warehouse.service.messaging.model.UserKafkaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductCreatedListener {

    private final ObjectMapper objectMapper;
    private final ProductMessageListener productMessageListener;
    private final ProductMessagingMapper productMessagingMapper;

    public ProductCreatedListener(ObjectMapper objectMapper, ProductMessageListener productMessageListener, ProductMessagingMapper productMessagingMapper) {
        this.objectMapper = objectMapper;
        this.productMessageListener = productMessageListener;
        this.productMessagingMapper = productMessagingMapper;
    }

    @KafkaListener(topics = "product_created", groupId = "warehouse-group")
    public void listen(String message) {
        try {
            ProductKafkaModel productKafkaModel = objectMapper.readValue(message, ProductKafkaModel.class);
            log.info("Received product_created event: {}", productKafkaModel);

            ProductModel productModel = productMessagingMapper.mapToProductModel(productKafkaModel);

            productMessageListener.savedProduct(productModel);

        } catch (Exception e) {
            log.error("Error processing product_created event", e);
        }
    }
}
