package com.fns.warehouse.service.domain.ports.output.repository;

import enitity.Order;

public interface OrderRepository {
    Order saveOrder(Order order);
}
