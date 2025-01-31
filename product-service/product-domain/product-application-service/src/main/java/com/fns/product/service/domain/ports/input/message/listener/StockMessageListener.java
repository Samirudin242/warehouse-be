package com.fns.product.service.domain.ports.input.message.listener;

import com.fns.product.service.domain.dto.message.StockModel;
import org.springframework.stereotype.Component;

public interface StockMessageListener {
    void updateStock(StockModel stockModel);
}
