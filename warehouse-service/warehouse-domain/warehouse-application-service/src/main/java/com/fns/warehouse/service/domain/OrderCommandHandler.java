package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.NearestWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.GetNearestWarehouseResponse;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.output.message.WarehouseMessagePublisher;
import com.fns.warehouse.service.domain.ports.output.repository.LocationRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.util.DistanceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderCommandHandler {

    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseRepository warehouseRepository;
    private final LocationRepository locationRepository;
    private final WarehouseMessagePublisher warehouseMessagePublisher;
    private final WarehouseDomainService warehouseDomainService;
    private final DistanceCalculator distanceCalculator;

    public OrderCommandHandler(WarehouseDataMapper warehouseDataMapper, WarehouseRepository warehouseRepository, LocationRepository locationRepository, WarehouseMessagePublisher warehouseMessagePublisher, WarehouseDomainService warehouseDomainService, DistanceCalculator distanceCalculator) {
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseRepository = warehouseRepository;
        this.locationRepository = locationRepository;
        this.warehouseMessagePublisher = warehouseMessagePublisher;
        this.warehouseDomainService = warehouseDomainService;
        this.distanceCalculator = distanceCalculator;
    }

    public GetNearestWarehouseResponse getNearestWarehouse(NearestWarehouseCommand nearestWarehouseCommand) {
        List<Warehouse> warehouseList = warehouseRepository.getAllWarehouse(nearestWarehouseCommand.getWarehouse());
        log.info("warehouseList, {}", warehouseList);

        Warehouse nearestWarehouse = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Warehouse warehouse : warehouseList) {
            assert false;
            double distance = distanceCalculator.calculateDistance(
                    nearestWarehouseCommand.getUser().getLatitude(), nearestWarehouseCommand.getUser().getLongitude(),
                    warehouse.getLatitude(), warehouse.getLongitude()
            );

            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestWarehouse = warehouse;
            }
        }

        if (nearestWarehouse == null) {
            throw new RuntimeException("No warehouse found");
        }
        return warehouseDataMapper.getNearestWarehouseResponse(nearestWarehouse);
    }
}
