package com.fns.product.service.domain.ports.output.message;

import com.fns.product.service.domain.dto.message.WarehouseModel;

public interface WarehouseMessageListener {
    void SavedWarehouse(WarehouseModel warehouseModel);
}
