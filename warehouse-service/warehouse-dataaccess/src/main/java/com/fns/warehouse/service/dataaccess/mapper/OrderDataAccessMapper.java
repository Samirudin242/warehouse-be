package com.fns.warehouse.service.dataaccess.mapper;

import com.fns.warehouse.service.dataaccess.entity.*;
import com.fns.warehouse.service.dataaccess.repository.*;
import enitity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OrderDataAccessMapper {

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final ProductColorJpaRepository productColorJpaRepository;
    private final ProductSizeJpaRepository productSizeJpaRepository;

    public OrderDataAccessMapper(WarehouseJpaRepository warehouseJpaRepository, UserJpaRepository userJpaRepository, ProductJpaRepository productJpaRepository, ProductColorJpaRepository productColorJpaRepository, ProductSizeJpaRepository productSizeJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.productColorJpaRepository = productColorJpaRepository;
        this.productSizeJpaRepository = productSizeJpaRepository;
    }

    public OrderEntity orderToOrderEntity(Order order) {

        WarehouseEntity warehouseEntity = warehouseJpaRepository.findById(order.getWarehouse_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse id"));
        UserEntity userEntity = userJpaRepository.findById(order.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

        return OrderEntity.builder()
                .id(order.getId())
                .user(userEntity)
                .order_date(order.getOrder_date())
                .total_amount(order.getTotal_amount())
                .total_shipping(order.getTotal_shipping())
                .warehouse(warehouseEntity)
                .user_address(order.getUser_address())
                .user_latitude(order.getUser_latitude())
                .user_longitude(order.getUser_longitude())
                .status(OrderEntity.OrderStatus.PAYMENT_WAITING)
                .build();
    }


    public List<OrderItemEntity> orderToOrderItemEntities(Order order, OrderEntity savedOrderEntity) {
        return order.getOrders().stream()
                .map(orderItem -> {
                    ProductEntity productEntity = productJpaRepository.findById(orderItem.getProduct_id())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + orderItem.getProduct_id()));

                    ProductColorEntity colorEntity = productColorJpaRepository.findById(orderItem.getColor_id())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid product color id: " + orderItem.getColor_id()));

                    ProductSizeEntity sizeEntity = productSizeJpaRepository.findById(orderItem.getSize_id())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid product size id: " + orderItem.getSize_id()));


                    return OrderItemEntity.builder()
                            .id(orderItem.getId())
                            .order(savedOrderEntity)
                            .quantity(orderItem.getQuantity())
                            .price(orderItem.getPrice())
                            .product(productEntity)
                            .productSize(sizeEntity)
                            .productColor(colorEntity)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public PaymentEntity orderToPayment(Order order, OrderEntity savedOrderEntity) {
        String payment = order.getPayment_method();

        return PaymentEntity.builder()
               .id(order.getId())
               .order(savedOrderEntity)
                .payment_type(Objects.equals(payment, "bank_transfer") ? PaymentEntity.PaymentType.BANK_TRANSFER : PaymentEntity.PaymentType.CASH)
               .payment_date(new Date())
               .build();
    }

    public Order orderFromEntity(OrderEntity order) {
        return Order.builder()
               .id(order.getId())
               .user_id(order.getUser().getId())
               .warehouse_id(order.getWarehouse().getId())
               .order_date(order.getOrder_date())
               .total_amount(order.getTotal_amount())
               .total_shipping(order.getTotal_shipping())
               .user_address(order.getUser_address())
               .user_latitude(order.getUser_latitude())
               .user_longitude(order.getUser_longitude())
               .build();
    }


}
