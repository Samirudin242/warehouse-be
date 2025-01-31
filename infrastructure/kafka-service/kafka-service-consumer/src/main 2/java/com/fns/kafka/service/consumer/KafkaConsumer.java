package com.fns.kafka.service.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(id = "myConsumer", topics = "user-created", groupId = "springboot-group-1", autoStartup = "false")
    public void listen(String value,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        logger.info(String.format("\n\n Consumed event from topic %s: key = %-10s value = %s \n\n", topic, key, value));
    }
}
