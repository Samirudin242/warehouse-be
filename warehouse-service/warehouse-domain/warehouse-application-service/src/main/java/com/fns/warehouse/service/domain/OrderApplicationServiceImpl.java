package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateOrderCommand;
import com.fns.warehouse.service.domain.dto.response.CreateOrderResponse;
import com.fns.warehouse.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCommandHandler orderCommandHandler;

    public OrderApplicationServiceImpl(OrderCommandHandler orderCommandHandler) {
        this.orderCommandHandler = orderCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCommandHandler.createOrder(createOrderCommand);
    }
}
