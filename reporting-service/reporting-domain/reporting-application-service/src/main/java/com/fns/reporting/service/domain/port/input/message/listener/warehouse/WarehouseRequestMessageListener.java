package com.fns.reporting.service.domain.port.input.message.listener.warehouse;

import com.fns.reporting.service.domain.dto.message.WarehouseRequest;


public interface WarehouseRequestMessageListener {
    void warehouseRequestCompleted(WarehouseRequest warehouseRequest);

    void warehouseRequestCanceled(WarehouseRequest warehouseRequest);

}
