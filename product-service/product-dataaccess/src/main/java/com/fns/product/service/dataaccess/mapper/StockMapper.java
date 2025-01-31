package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.StockEntity;
import com.fns.product.service.dataaccess.entity.WarehouseEntity;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.dataaccess.repository.WarehouseJpaRepository;
import com.fns.product.service.domain.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    private final ProductJpaRepository productJpaRepository;
    private final WarehouseJpaRepository warehouseJpaRepository;

    public StockMapper(ProductJpaRepository productJpaRepository, WarehouseJpaRepository warehouseJpaRepository) {
        this.productJpaRepository = productJpaRepository;
        this.warehouseJpaRepository = warehouseJpaRepository;
    }

    public StockEntity stockEntityFromStock(Stock stock) {

        ProductEntity productEntity = productJpaRepository.findById(stock.getProduct_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + stock.getProduct_id()));

        WarehouseEntity warehouseEntity = warehouseJpaRepository.findById(stock.getWarehouse_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse id: " + stock.getWarehouse_id()));

        // Map the Stock to StockEntity
        return StockEntity.builder()
                .id(stock.getId())
                .warehouse(warehouseEntity)
                .product(productEntity)
                .quantity(stock.getQuantity())
                .build();
    }

    public Stock stockFromStockEntity(StockEntity stockEntity) {
        // Map the StockEntity to Stock
        return Stock.builder()
                .id(stockEntity.getId())
                .warehouse_id(stockEntity.getWarehouse().getId())
                .product_id(stockEntity.getProduct().getId())
                .quantity(stockEntity.getQuantity())
                .build();
    }

}
