package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.Warehouse;

public interface WarehouseRepository {
    void saveWarehouse(Warehouse warehouse);
}
