package com.fns.product.service.domain.ports.input.message.listener;

import com.fns.product.service.domain.dto.message.WarehouseModel;

public interface WarehouseMessageListener {
    void savedWarehouse(WarehouseModel warehouse);
}
