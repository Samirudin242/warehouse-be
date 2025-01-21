package com.fns.product.service.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.product.service.domain.dto.message.UserModel;
import com.fns.product.service.domain.ports.input.message.listener.UserMessageListener;
import com.fns.product.service.messaging.mapper.UserMessagingMapper;
import com.fns.product.service.messaging.model.UserKafkaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCreatedListener {

    private final ObjectMapper objectMapper;
    private final UserMessageListener userMessageListener;
    private final UserMessagingMapper userMessagingMapper;

    public UserCreatedListener(ObjectMapper objectMapper, UserMessageListener userMessageListener, UserMessagingMapper userMessagingMapper) {
        this.objectMapper = objectMapper;
        this.userMessageListener = userMessageListener;
        this.userMessagingMapper = userMessagingMapper;
    }

    @KafkaListener(topics = "user_created", groupId = "product-group")
    public void listen(String message) {
        try {
            UserKafkaModel userKafkaModel = objectMapper.readValue(message, UserKafkaModel.class);
            log.info("Received user_created event: {}", userKafkaModel);

            UserModel userModel = userMessagingMapper.mapToUserModel(userKafkaModel);
            userMessageListener.savedUser(userModel);

        } catch (Exception e) {
            log.error("Error processing user_created event", e);
        }
    }
}
