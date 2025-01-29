package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateOrderCommand;
import com.fns.warehouse.service.domain.dto.create.NearestWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.GetNearestWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.response.CreateOrderResponse;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.mapper.OrderDataMapper;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.output.message.WarehouseMessagePublisher;
import com.fns.warehouse.service.domain.ports.output.repository.LocationRepository;
import com.fns.warehouse.service.domain.ports.output.repository.OrderRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.util.DistanceCalculator;
import enitity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class OrderCommandHandler {

    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseRepository warehouseRepository;
    private final DistanceCalculator distanceCalculator;
    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    public OrderCommandHandler(WarehouseDataMapper warehouseDataMapper, WarehouseRepository warehouseRepository, DistanceCalculator distanceCalculator, OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseRepository = warehouseRepository;
        this.distanceCalculator = distanceCalculator;
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
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

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        Order order = orderDataMapper.commandToOrder(createOrderCommand);

        Order savedOrder = orderRepository.saveOrder(order);

        return orderDataMapper.orderResponse(savedOrder);
    }

    public List<GetOrderResponse> getAllOrder(UUID userId) {

        return orderRepository.getAllOrder(userId);
    }
}
