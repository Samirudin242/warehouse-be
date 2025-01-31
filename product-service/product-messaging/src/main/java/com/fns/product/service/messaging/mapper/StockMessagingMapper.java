package com.fns.product.service.messaging.mapper;

import com.fns.product.service.domain.dto.message.StockModel;
import com.fns.product.service.messaging.model.StockKafkaModel;
import org.springframework.stereotype.Component;

@Component
public class StockMessagingMapper {
    public StockModel getStockModel(StockKafkaModel stockKafkaModel) {
        return StockModel.builder()
                .id(stockKafkaModel.getId())
                .product_id(stockKafkaModel.getProduct_id())
                .warehouse_id(stockKafkaModel.getWarehouse_id())
                .quantity(stockKafkaModel.getQuantity())
                .build();
    }
}
