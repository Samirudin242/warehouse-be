package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.StockEntity;
import com.fns.product.service.domain.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockEntity stockEntityFromStock(Stock stock) {
        // Map the Stock to StockEntity
        return StockEntity.builder()
                .id(stock.getId())
                .warehouseId(stock.getWarehouse_id())
                .productId(stock.getProduct_id())
                .quantity(stock.getQuantity())
                .build();
    }

    public Stock stockFromStockEntity(StockEntity stockEntity) {
        // Map the StockEntity to Stock
        return Stock.builder()
                .id(stockEntity.getId())
                .warehouse_id(stockEntity.getWarehouseId())
                .product_id(stockEntity.getProductId())
                .quantity(stockEntity.getQuantity())
                .build();
    }

}
