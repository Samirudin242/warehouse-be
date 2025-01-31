package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.message.StockModel;
import com.fns.product.service.domain.ports.input.message.listener.StockMessageListener;
import com.fns.product.service.domain.ports.output.repository.StockRepository;
import org.springframework.stereotype.Component;

@Component
public class StockMessagingImpl implements StockMessageListener {

    private final StockRepository stockRepository;

    public StockMessagingImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void updateStock(StockModel stockModel) {
        stockRepository.updateStockFromMessaging(stockModel);
    }
}
