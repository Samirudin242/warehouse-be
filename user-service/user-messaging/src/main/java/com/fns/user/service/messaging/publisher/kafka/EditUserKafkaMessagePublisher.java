package com.fns.user.service.messaging.publisher.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.user.service.domain.event.UserEditEvent;
import com.fns.user.service.domain.ports.output.message.UserEditMessagePublisher;
import com.fns.user.service.messaging.mapper.UserKafkaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EditUserKafkaMessagePublisher implements UserEditMessagePublisher {

    private static final String TOPIC = "user-edited";

    private final UserKafkaMapper userKafkaMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public EditUserKafkaMessagePublisher(UserKafkaMapper userKafkaMapper, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.userKafkaMapper = userKafkaMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void publish(UserEditEvent domainEvent) {

        try {
            String message = objectMapper.writeValueAsString(userKafkaMapper.convertToUser(domainEvent.getEntity()));
            log.info("Publishing user edited {}", message);
            // Publish to Kafka
            kafkaTemplate.send(TOPIC, message);

        } catch (Exception e) {
            log.error("Failed to publish user_created event to Kafka", e);
            throw new RuntimeException("Error publishing user_created event to Kafka", e);
        }

    }
}
