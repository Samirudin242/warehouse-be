package com.fns.user.service.messaging.publisher.kafka;

import com.fns.user.service.domain.event.UserCreatedEvent;
import com.fns.user.service.domain.ports.output.message.UserMessagePublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.user.service.messaging.mapper.UserKafkaMapper;
import com.fns.user.service.messaging.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateUserKafkaMessagePublisher implements UserMessagePublisher {

    private final UserKafkaMapper userKafkaMapper;
    private KafkaProducerService kafkaProducerService;

    private static final Logger logger = LoggerFactory.getLogger(CreateUserKafkaMessagePublisher.class);

    private static final String TOPIC = "user_created";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;


    @Autowired
    public CreateUserKafkaMessagePublisher(UserKafkaMapper userKafkaMapper, UserKafkaMapper userKafkaMapper1, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.userKafkaMapper = userKafkaMapper1;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(UserCreatedEvent domainEvent) {
        try {
            logger.info("Publishing");
            // Convert domain event to JSON
            String message = objectMapper.writeValueAsString(userKafkaMapper.convertToUser(domainEvent.getEntity()));

            // Publish to Kafka
            kafkaTemplate.send(TOPIC, message);

            logger.info("Published user_created event to Kafka topic {}: {}", TOPIC, message);
        } catch (Exception e) {
            logger.error("Failed to publish user_created event to Kafka", e);
            throw new RuntimeException("Error publishing user_created event to Kafka", e);
        }
    }
}
