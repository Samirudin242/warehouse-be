package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.entity.WarehouseEntity;
import com.fns.warehouse.service.dataaccess.mapper.WarehouseDataAccessMapper;
import com.fns.warehouse.service.dataaccess.repository.WarehouseJpaRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseRepositoryImpl implements WarehouseRepository {
    private final WarehouseJpaRepository warehouseJpaRepository;
    private final WarehouseDataAccessMapper warehouseDataAccessMapper;

    public WarehouseRepositoryImpl(WarehouseJpaRepository warehouseJpaRepository, WarehouseDataAccessMapper warehouseDataAccessMapper) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.warehouseDataAccessMapper = warehouseDataAccessMapper;
    }


    @Override
    public Warehouse saveWarehouse(Warehouse warehouse) {
        WarehouseEntity entity = warehouseDataAccessMapper.warehouseToWarehouseEntity(warehouse);
        WarehouseEntity savedEntity = warehouseJpaRepository.save(entity);
        return warehouseDataAccessMapper.warehouseEntityToWarehouse(savedEntity);
    }
}
