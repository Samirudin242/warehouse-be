package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.mapper.StockDataAccessMapper;
import com.fns.warehouse.service.dataaccess.repository.StockJpaRepository;
import com.fns.warehouse.service.domain.entity.Stock;
import com.fns.warehouse.service.domain.ports.output.repository.StockRepository;
import org.springframework.stereotype.Component;

@Component
public class StockRepositoryImpl implements StockRepository {

    private final StockJpaRepository stockJpaRepository;
    private final StockDataAccessMapper stockDataAccessMapper;

    public StockRepositoryImpl(StockJpaRepository stockJpaRepository, StockDataAccessMapper stockDataAccessMapper) {
        this.stockJpaRepository = stockJpaRepository;
        this.stockDataAccessMapper = stockDataAccessMapper;
    }

    @Override
    public void saveStock(Stock stock) {
        stockJpaRepository.save(stockDataAccessMapper.stockToStockEntity(stock));
    }
}
