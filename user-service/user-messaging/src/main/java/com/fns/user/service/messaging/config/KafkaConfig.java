package com.fns.user.service.messaging.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    //cloud configuration
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-ldvr1.asia-southeast1.gcp.confluent.cloud:9092");
//        props.put("sasl.mechanism", "PLAIN");
//        props.put("sasl.jaas.config",
//                "org.apache.kafka.common.security.plain.PlainLoginModule required username='A2VCCYFEXERRJFK7' password='T1PRDVTCvX0z7sKX3daWHwJC0KS8rWIJwjKrsvIiMJkwEVqIWkEH8YQESlHSS4ni';");
//        props.put("security.protocol", "SASL_SSL");
//        props.put("session.timeout.ms", "45000");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return props;
//    }

    //localhost config
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        // Use the local Kafka brokers defined in Docker Compose
        // Assuming you want to connect to 'kafka-broker-1:9092'
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092,localhost:29092,localhost:39092");

        // Remove the SASL configuration as it's not needed for local Kafka setup
        props.put("security.protocol", "PLAINTEXT"); // This is used for local connections, no SSL or SASL

        // Timeout settings and serialization options
        props.put("session.timeout.ms", "45000");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
