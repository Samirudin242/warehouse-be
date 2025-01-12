package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.StockEntity;
import com.fns.product.service.dataaccess.mapper.StockMapper;
import com.fns.product.service.dataaccess.repository.StockJpaRepository;
import com.fns.product.service.domain.entity.Stock;
import com.fns.product.service.domain.ports.output.repository.StockRepository;
import org.springframework.stereotype.Component;

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
}
