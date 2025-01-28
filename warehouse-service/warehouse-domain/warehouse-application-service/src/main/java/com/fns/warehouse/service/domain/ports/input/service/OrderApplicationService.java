package com.fns.warehouse.service.domain.ports.input.service;

import com.fns.warehouse.service.domain.dto.create.CreateOrderCommand;
import com.fns.warehouse.service.domain.dto.response.CreateOrderResponse;
import org.springframework.stereotype.Component;

@Component
public interface OrderApplicationService {
    CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand);
}
