package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.dto.message.StockModel;
import com.fns.product.service.domain.entity.Stock;

import java.util.List;
import java.util.UUID;

public interface StockRepository {
    Stock saveStock(Stock stock);

    List<Stock> findByProductId(UUID productId);

    void updateStockFromMessaging(StockModel stockModel);

}
