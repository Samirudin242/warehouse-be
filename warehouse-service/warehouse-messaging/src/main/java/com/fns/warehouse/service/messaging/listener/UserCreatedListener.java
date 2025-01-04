package com.fns.warehouse.service.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.warehouse.service.domain.dto.message.UserModel;
import com.fns.warehouse.service.domain.ports.input.message.UserMessageListener;
import com.fns.warehouse.service.messaging.mapper.WarehouseMessagingMapper;
import com.fns.warehouse.service.messaging.model.UserKafkaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCreatedListener {

    private final ObjectMapper objectMapper;
    private final UserMessageListener userMessageListener;
    private final WarehouseMessagingMapper warehouseMessagingMapper;

    public UserCreatedListener(ObjectMapper objectMapper, UserMessageListener userMessageListener, WarehouseMessagingMapper warehouseMessagingMapper) {
        this.objectMapper = objectMapper;
        this.userMessageListener = userMessageListener;
        this.warehouseMessagingMapper = warehouseMessagingMapper;
    }

    @KafkaListener(topics = "user_created", groupId = "warehouse-group")
    public void listen(String message) {
        try {
            UserKafkaModel userKafkaModel = objectMapper.readValue(message, UserKafkaModel.class);
            log.info("Received user_created event: {}", userKafkaModel);

            UserModel userModel = warehouseMessagingMapper.mapToUserModel(userKafkaModel);

            userMessageListener.savedUser(userModel);

        } catch (Exception e) {
            log.error("Error processing user_created event", e);
        }
    }

}
