package com.fns.user.service.messaging.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.user.service.messaging.publisher.kafka.CreateUserKafkaMessagePublisher;
import com.fns.user.service.messaging.mapper.UserKafkaMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class MessagingConfig {

    @Bean
    public CreateUserKafkaMessagePublisher userMessagePublisher(
            UserKafkaMapper userKafkaMapper,
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        return new CreateUserKafkaMessagePublisher(userKafkaMapper, userKafkaMapper, kafkaTemplate, objectMapper);
    }
}

