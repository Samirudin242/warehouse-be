package com.fns.kafka.producer.service;

import org.springframework.kafka.support.SendResult;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.util.concurrent.ListenableFutureCallback;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

import java.io.Serializable;

public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase>  {
    void send(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback);
}
