package com.fns.warehouse.service.dataaccess.mapper;

import com.fns.warehouse.service.dataaccess.entity.ProductEntity;
import com.fns.warehouse.service.dataaccess.entity.StockEntity;
import com.fns.warehouse.service.dataaccess.entity.WarehouseEntity;
import com.fns.warehouse.service.dataaccess.repository.ProductJpaRepository;
import com.fns.warehouse.service.dataaccess.repository.WarehouseJpaRepository;
import com.fns.warehouse.service.domain.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockDataAccessMapper {

    private final ProductJpaRepository productJpaRepository;
    private final WarehouseJpaRepository warehouseJpaRepository;

    public StockDataAccessMapper(ProductJpaRepository productJpaRepository, WarehouseJpaRepository warehouseJpaRepository) {
        this.productJpaRepository = productJpaRepository;
        this.warehouseJpaRepository = warehouseJpaRepository;
    }

    public StockEntity stockToStockEntity(Stock stock) {

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

}
