package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.Stock;

public interface StockRepository {
    Stock saveStock(Stock stock);
}
