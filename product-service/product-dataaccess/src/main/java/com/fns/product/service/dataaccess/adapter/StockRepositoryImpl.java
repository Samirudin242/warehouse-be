package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.StockEntity;
import com.fns.product.service.dataaccess.mapper.StockMapper;
import com.fns.product.service.dataaccess.repository.StockJpaRepository;
import com.fns.product.service.domain.dto.message.StockModel;
import com.fns.product.service.domain.entity.Stock;
import com.fns.product.service.domain.ports.output.repository.StockRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StockRepositoryImpl implements StockRepository {
    private final StockMapper stockMapper;
    private final StockJpaRepository stockJpaRepository;

    public StockRepositoryImpl(StockMapper stockMapper, StockJpaRepository stockJpaRepository) {
        this.stockMapper = stockMapper;
        this.stockJpaRepository = stockJpaRepository;
    }

    @Override
    public Stock saveStock(Stock stock) {
        StockEntity entity = stockJpaRepository.save(stockMapper.stockEntityFromStock(stock));
        return stockMapper.stockFromStockEntity(entity);
    }

    @Override
    public List<Stock> findByProductId(UUID productId) {
        List<StockEntity> allStock = stockJpaRepository.findAllByProduct_Id(productId);

        // Map StockEntity to Stock
        return allStock.stream()
                .map(entity -> Stock.builder()
                        .id(entity.getId())
                        .warehouse_id(entity.getWarehouse().getId())
                        .product_id(entity.getProduct().getId())
                        .quantity(entity.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void updateStockFromMessaging(StockModel stockModel) {
        StockEntity stockEntity = stockJpaRepository.findByProduct_IdAndWarehouse_Id(stockModel.getProduct_id(), stockModel.getWarehouse_id())
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        stockEntity.setQuantity(stockEntity.getQuantity() - stockModel.getQuantity());

        stockJpaRepository.save(stockEntity);
    }
}
