package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.entity.Stock;

public interface StockRepository {
    void saveStock(Stock stock);
}
