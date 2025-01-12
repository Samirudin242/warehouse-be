package com.fns.warehouse.service.dataaccess.mapper;

import com.fns.warehouse.service.dataaccess.entity.StockEntity;
import com.fns.warehouse.service.domain.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockDataAccessMapper {

    public StockEntity stockToStockEntity(Stock stock) {
        // Map the Stock to StockEntity
        return StockEntity.builder()
                .id(stock.getId())
                .warehouse_id(stock.getWarehouse_id())
                .product_id(stock.getProduct_id())
                .quantity(stock.getQuantity())
                .build();
    }

}
