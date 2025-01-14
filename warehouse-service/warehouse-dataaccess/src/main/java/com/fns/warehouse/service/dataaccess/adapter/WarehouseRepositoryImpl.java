package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.entity.WarehouseEntity;
import com.fns.warehouse.service.dataaccess.mapper.WarehouseDataAccessMapper;
import com.fns.warehouse.service.dataaccess.repository.WarehouseJpaRepository;
import com.fns.warehouse.service.domain.dto.get.GetAllWarehouseResponse;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page<GetAllWarehouseResponse> getAllWarehouse(Integer page, Integer size, String name) {
        Pageable pageable = PageRequest.of(page, size);

        Page<WarehouseEntity> warehouseEntitiesPage;

        if(name.isEmpty()) {
            warehouseEntitiesPage = warehouseJpaRepository.findAll(pageable);
        } else {
            warehouseEntitiesPage = warehouseJpaRepository.findByNameILike(name, pageable);
        }

        List<GetAllWarehouseResponse> getAllWarehouseResponses = warehouseEntitiesPage.getContent().stream()
                .map(warehouseEntity -> {
                    Warehouse warehouse = warehouseDataAccessMapper.warehouseEntityToWarehouseGet(warehouseEntity);

                    return GetAllWarehouseResponse.builder()
                            .id(warehouse.getId())
                            .name(warehouse.getName())
                            .location(warehouse.getAddress())
                            .province(warehouse.getProvince())
                            .city(warehouse.getCity())
                            .build();
                }).toList();

        return new PageImpl<>(getAllWarehouseResponses, pageable, warehouseEntitiesPage.getTotalElements());
    }


}
