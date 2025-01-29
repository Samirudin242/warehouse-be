package com.fns.warehouse.service.domain.ports.input.service;

import com.fns.warehouse.service.domain.dto.create.CreateOrderCommand;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.response.CreateOrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface OrderApplicationService {
    CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand);

    List<GetOrderResponse> getAllOrderUser(UUID userId);
}
