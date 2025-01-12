package com.fns.product.service.messaging.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.product.service.messaging.mapper.ProductMessagingMapper;
import com.fns.product.service.messaging.publisher.kafka.CreateProductKafkaMessagePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class MessagingConfig {

    @Bean
    public CreateProductKafkaMessagePublisher userMessagePublisher(
            ProductMessagingMapper productMessagingMapper,
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        return new CreateProductKafkaMessagePublisher(productMessagingMapper, kafkaTemplate, objectMapper);
    }
}

