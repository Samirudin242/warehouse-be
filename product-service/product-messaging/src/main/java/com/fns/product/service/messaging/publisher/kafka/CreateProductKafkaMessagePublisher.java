package com.fns.product.service.messaging.publisher.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.product.service.domain.event.ProductCreatedEvent;
import com.fns.product.service.domain.ports.output.message.ProductMessagePublisher;
import com.fns.product.service.messaging.mapper.ProductMessagingMapper;
import com.fns.product.service.messaging.producer.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class CreateProductKafkaMessagePublisher implements ProductMessagePublisher {
    private final ProductMessagingMapper productMessagingMapper;
    private KafkaProducerService kafkaProducerService;

    private static final Logger logger = LoggerFactory.getLogger(CreateProductKafkaMessagePublisher.class);

    private static final String TOPIC = "product_created";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;


    @Autowired
    public CreateProductKafkaMessagePublisher(ProductMessagingMapper userKafkaMapper, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.productMessagingMapper = userKafkaMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(ProductCreatedEvent domainEvent) {
        try {
            logger.info("Publishing");
            // Convert domain event to JSON
            String message = objectMapper.writeValueAsString(productMessagingMapper.covertToProductKafkaModel(domainEvent.getEntity()));

            // Publish to Kafka
            kafkaTemplate.send(TOPIC, message);

            logger.info("Published user_created event to Kafka topic {}: {}", TOPIC, message);
        } catch (Exception e) {
            logger.error("Failed to publish user_created event to Kafka", e);
            throw new RuntimeException("Error publishing user_created event to Kafka", e);
        }
    }
}
