package com.fns.product.service.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fns.product.service.messaging.mapper.StockMessagingMapper;
import com.fns.product.service.messaging.model.StockKafkaModel;
import com.fns.product.service.domain.ports.input.message.listener.StockMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockUpdatedListener {
    private final ObjectMapper objectMapper;
    private final StockMessagingMapper stockMessagingMapper;
    private final StockMessageListener stockMessageListener;

    public StockUpdatedListener(ObjectMapper objectMapper, StockMessagingMapper stockMessagingMapper, StockMessageListener stockMessageListener) {
        this.objectMapper = objectMapper;
        this.stockMessagingMapper = stockMessagingMapper;
        this.stockMessageListener = stockMessageListener;
    }

    @KafkaListener(topics = "stock-updated", groupId = "product-group")
    public void listen(String message) {
        try {
            StockKafkaModel stockKafkaModel = objectMapper.readValue(message, StockKafkaModel.class);
            log.info("Received stock_updated event: {}", stockKafkaModel);
            stockMessageListener.updateStock(stockMessagingMapper.getStockModel(stockKafkaModel));
        } catch (Exception e) {
            log.info("Error processing stock_updated event: {}", e.getMessage());
        }
    }
}
