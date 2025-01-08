package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.message.WarehouseModel;
import com.fns.product.service.domain.entity.Warehouse;
import com.fns.product.service.domain.mapper.WarehouseDataMapper;
import com.fns.product.service.domain.ports.input.message.listener.WarehouseMessageListener;
import com.fns.product.service.domain.ports.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WarehouseMessageImpl implements WarehouseMessageListener {

    private WarehouseRepository warehouseRepository;
    private WarehouseDataMapper warehouseDataMapper;

    public WarehouseMessageImpl(WarehouseRepository warehouseRepository, WarehouseDataMapper warehouseDataMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseDataMapper = warehouseDataMapper;
    }

    @Override
    public void savedWarehouse(WarehouseModel warehouse) {
        try {
//            Warehouse warehouse
        } catch (Exception e) {
            log.error("Failed to save warehouse: {}", warehouse, e);
            throw new RuntimeException(e);
        }
    }
}
