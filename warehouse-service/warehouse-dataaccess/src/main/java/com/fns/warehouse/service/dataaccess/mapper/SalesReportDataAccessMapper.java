package com.fns.warehouse.service.dataaccess.mapper;

import com.fns.warehouse.service.dataaccess.entity.OrderItemEntity;
import com.fns.warehouse.service.dataaccess.entity.ProductEntity;
import com.fns.warehouse.service.dataaccess.entity.SalesReportEntity;
import com.fns.warehouse.service.dataaccess.entity.WarehouseEntity;
import com.fns.warehouse.service.dataaccess.repository.OrderItemJpaRepository;
import com.fns.warehouse.service.dataaccess.repository.ProductJpaRepository;
import com.fns.warehouse.service.dataaccess.repository.WarehouseJpaRepository;
import com.fns.warehouse.service.domain.dto.create.CreateSalesCommand;
import org.springframework.stereotype.Component;

@Component
public class SalesReportDataAccessMapper {

    private final ProductJpaRepository productJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final WarehouseJpaRepository warehouseJpaRepository;

    public SalesReportDataAccessMapper(ProductJpaRepository productJpaRepository, OrderItemJpaRepository orderItemJpaRepository, WarehouseJpaRepository warehouseJpaRepository) {
        this.productJpaRepository = productJpaRepository;
        this.orderItemJpaRepository = orderItemJpaRepository;
        this.warehouseJpaRepository = warehouseJpaRepository;
    }

    public SalesReportEntity salesReportEntity (CreateSalesCommand createSalesCommand) {

        OrderItemEntity orderItemEntity = orderItemJpaRepository.findById(createSalesCommand.getOrder_item_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid order item id: " + createSalesCommand.getOrder_item_id()));

        ProductEntity productEntity = productJpaRepository.findById(createSalesCommand.getProduct_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + createSalesCommand.getProduct_id()));

        WarehouseEntity warehouseEntity = warehouseJpaRepository.findById(createSalesCommand.getWarehouse_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse id: " + createSalesCommand.getWarehouse_id()));


        return SalesReportEntity.builder()
                .product(productEntity)
                .orderItem(orderItemEntity)
                .warehouses(warehouseEntity)
                .quantity(createSalesCommand.getQuantity())
                .totalPrice(createSalesCommand.getTotal_price())
                .transactionDate(createSalesCommand.getTransaction_date())
                .build();
    }

}
