package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.*;
import com.fns.warehouse.service.domain.dto.get.GetNearestWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.response.CreateOrderResponse;
import com.fns.warehouse.service.domain.dto.response.ShippingOrderResponse;
import com.fns.warehouse.service.domain.dto.response.UpdateStockResponse;
import com.fns.warehouse.service.domain.dto.response.UploadPaymentResponse;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.event.StockUpdateEvent;
import com.fns.warehouse.service.domain.mapper.OrderDataMapper;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.output.message.StockUpdateMessagePublisher;
import com.fns.warehouse.service.domain.ports.output.message.WarehouseMessagePublisher;
import com.fns.warehouse.service.domain.ports.output.repository.LocationRepository;
import com.fns.warehouse.service.domain.ports.output.repository.OrderRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.util.DistanceCalculator;
import enitity.MutationType;
import enitity.Order;
import enitity.OrderItem;
import enitity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    private final WarehouseDomainService warehouseDomainService;
    private final StockUpdateMessagePublisher stockUpdateMessagePublisher;

    public OrderCommandHandler(WarehouseDataMapper warehouseDataMapper, WarehouseRepository warehouseRepository, DistanceCalculator distanceCalculator, OrderDataMapper orderDataMapper, OrderRepository orderRepository, WarehouseDomainService warehouseDomainService, StockUpdateMessagePublisher stockUpdateMessagePublisher) {
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseRepository = warehouseRepository;
        this.distanceCalculator = distanceCalculator;
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
        this.warehouseDomainService = warehouseDomainService;
        this.stockUpdateMessagePublisher = stockUpdateMessagePublisher;
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

    public UploadPaymentResponse uploadPayment(UploadPaymentCommand uploadPaymentCommand) {
        try {
            return orderRepository.uploadPayment(uploadPaymentCommand);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading payment");
        }
    }

    @Transactional
    public ShippingOrderResponse shipOrder(ShippingOrderCommand shippingOrderCommand) {
        try {

            List<CreateOrderItemCommand> orders = shippingOrderCommand.getOrders();

            for (CreateOrderItemCommand order : orders) {
                Integer quantity = order.getQuantity();
                List<UUID> warehouseIds = order.getWarehouse_id();
                boolean isTransferStock = false;
                UUID fromWarehouseId = null;

                for (UUID warehouseId : warehouseIds) {
                    UpdateStockCommand updateStockCommand = UpdateStockCommand.builder()
                            .orderId(shippingOrderCommand.getOrder_id())
                            .productId(order.getProduct_id())
                            .warehouseId(warehouseId)
                            .quantity(quantity)
                            .isTransferStock(isTransferStock)
                            .fromWarehouseId(fromWarehouseId)
                            .price(order.getPrice())
                            .build();

                    //update stock
                    UpdateStockResponse orderStock = orderRepository.updateStock(updateStockCommand);

                    //Create mutation stock;
                    CreateMutationStock createMutationStock = CreateMutationStock.builder()
                            .to_warehouse_id(warehouseId)
                            .from_warehouse_id(fromWarehouseId)
                            .mutation_date(new Date())
                            .product_id(order.getProduct_id())
                            .quantity(quantity)
                            .mutation_type(fromWarehouseId != null ? MutationType.TRANSFER : MutationType.OUT)
                            .build();

                    orderRepository.createMutationStock(createMutationStock);

                    //publish stock update
                    StockUpdateEvent stockUpdateEvent = warehouseDomainService.stockUpdateEvent(
                            orderStock.getStock_id(),
                            orderStock.getUpdateStock(),
                            orderStock.getProductId(),
                            orderStock.getWarehouseId(),
                            stockUpdateMessagePublisher
                    );

                    stockUpdateMessagePublisher.publish(stockUpdateEvent);

                    if(!orderStock.getIsUpdateAnotherStock())  {
                        break;
                    }

                    quantity = orderStock.getUpdateStockLeft();
                    isTransferStock = true;
                    fromWarehouseId = orderStock.getWarehouseId();

                }

                CreateSalesCommand createSalesCommand = orderDataMapper.createSalesCommand(order, warehouseIds.get(0), order.getId());

                //create sales
                orderRepository.createSales(createSalesCommand);

            }

            return orderRepository.shipOrder(shippingOrderCommand);
        } catch (Exception e) {
            throw new RuntimeException("Error shipping order");
        }
    }
}
