package com.fns.warehouse.service.dataaccess.mapper;

import com.fns.warehouse.service.dataaccess.entity.*;
import com.fns.warehouse.service.dataaccess.repository.*;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.get.OrderItemResponse;
import com.fns.warehouse.service.domain.dto.get.PaymentResponse;
import com.fns.warehouse.service.domain.entity.*;
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
                .orderDate(order.getOrder_date())
                .total_amount(order.getTotal_amount())
                .total_shipping(order.getTotal_shipping())
                .warehouse(warehouseEntity)
                .user_address(order.getUser_address())
                .user_latitude(order.getUser_latitude())
                .user_longitude(order.getUser_longitude())
                .status(OrderStatus.PAYMENT_WAITING)
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
                            .warehouse_id(orderItem.getWarehouse())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public PaymentEntity orderToPayment(Order order, OrderEntity savedOrderEntity) {
        String payment = order.getPayment_method();

        return PaymentEntity.builder()
               .id(order.getId())
               .order(savedOrderEntity)
                .payment_type(Objects.equals(payment, "bank_transfer") ? PaymentType.BANK_TRANSFER : PaymentType.CASH)
               .payment_date(new Date())
               .build();
    }

    public Order orderFromEntity(OrderEntity order) {
        return Order.builder()
               .id(order.getId())
               .user_id(order.getUser().getId())
               .warehouse_id(order.getWarehouse().getId())
               .order_date(order.getOrderDate())
               .total_amount(order.getTotal_amount())
               .total_shipping(order.getTotal_shipping())
               .user_address(order.getUser_address())
               .user_latitude(order.getUser_latitude())
               .user_longitude(order.getUser_longitude())
               .build();
    }

    public GetOrderResponse getOrderResponse(OrderEntity orderEntity) {
        return GetOrderResponse.builder()
               .id(orderEntity.getId())
               .order_date(orderEntity.getOrderDate())
               .total_amount(orderEntity.getTotal_amount())
               .total_shipping(orderEntity.getTotal_shipping())
               .user_address(orderEntity.getUser_address())
               .user_latitude(orderEntity.getUser_latitude())
               .user_longitude(orderEntity.getUser_longitude())
               .status(orderEntity.getStatus())
               .payment(orderEntity.getPayment() == null ? null : entityToResponsePayment(orderEntity.getPayment()))
                .order_items(entityToOrderItemResponse(orderEntity.getItems()))
                .build();
    }

    public List<OrderItemResponse> entityToOrderItemResponse(List<OrderItemEntity> orderItemEntity) {

        return orderItemEntity.stream().map( orderItem -> {

            ProductEntity productEntity = orderItem.getProduct();
            ProductColorEntity colorEntity = orderItem.getProductColor();
            ProductSizeEntity sizeEntity = orderItem.getProductSize();

            Product product = Product.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .description(productEntity.getDescription())
                    .build();

            ProductColor productColor = ProductColor.builder()
                    .id(colorEntity.getId())
                    .originalName(colorEntity.getOriginalName())
                    .build();

            ProductSizes productSizes = ProductSizes.builder()
                    .id(sizeEntity.getId())
                    .size(sizeEntity.getSize())
                    .build();

            return OrderItemResponse.builder()
                   .id(orderItem.getId())
                   .quantity(orderItem.getQuantity())
                   .price(orderItem.getPrice())
                   .product(product)
                   .productColor(productColor)
                    .productSize(productSizes)
                   .warehouse_id(orderItem.getWarehouse_id())
                   .build();
        }).toList();
    }


    public PaymentResponse entityToResponsePayment(PaymentEntity  paymentEntity) {
        return PaymentResponse.builder()
               .id(paymentEntity.getId())
               .payment_type(paymentEntity.getPayment_type())
               .payment_date(paymentEntity.getPayment_date())
               .build();
    }


    public List<GetOrderResponse> getAllOrders(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
               .map(this::getOrderResponse)
               .collect(Collectors.toList());
    }

}
