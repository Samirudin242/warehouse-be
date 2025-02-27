package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.LocationEntity;
import com.fns.product.service.dataaccess.entity.WarehouseEntity;
import com.fns.product.service.dataaccess.mapper.WarehouseDataAccessMapper;
import com.fns.product.service.dataaccess.repository.LocationJpaRepository;
import com.fns.product.service.dataaccess.repository.WarehouseJpaRepository;
import com.fns.product.service.domain.entity.Warehouse;
import com.fns.product.service.domain.mapper.WarehouseDataMapper;
import com.fns.product.service.domain.ports.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class WarehouseRepositoryImpl implements WarehouseRepository {

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    private final WarehouseDataAccessMapper warehouseDataMapper;

    public WarehouseRepositoryImpl(WarehouseJpaRepository warehouseJpaRepository, LocationJpaRepository locationJpaRepository, WarehouseDataAccessMapper warehouseDataMapper) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.locationJpaRepository = locationJpaRepository;
        this.warehouseDataMapper = warehouseDataMapper;
    }

    @Override
    @Transactional
    public void saveWarehouse(Warehouse warehouse) {
        log.info("Saving warehouse: {}", warehouse);
        WarehouseEntity warehouseEntity = warehouseDataMapper.warehouseToWarehouseEntity(warehouse);
        warehouseJpaRepository.save(warehouseEntity);
        locationJpaRepository.save(warehouseDataMapper.locationEntityFromWarehouse(warehouse, warehouseEntity));
    }

}
