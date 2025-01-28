package com.fns.warehouse.service.domain.mapper;

import com.fns.warehouse.service.domain.dto.create.CreateOrderCommand;
import com.fns.warehouse.service.domain.dto.create.CreateOrderItemCommand;
import com.fns.warehouse.service.domain.dto.response.CreateOrderResponse;
import enitity.Order;
import enitity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OrderDataMapper {

    public Order commandToOrder(CreateOrderCommand createOrderCommand) {
        Order order = Order.builder()
                .user_id(createOrderCommand.getUser_id())
                .order_date(new Date())
                .total_amount(createOrderCommand.getTotal_amount())
                .total_shipping(createOrderCommand.getTotal_shipping())
                .user_address(createOrderCommand.getUser_address())
                .user_latitude(createOrderCommand.getLatitude_user_address())
                .user_longitude(createOrderCommand.getLongitude_user_address())
                .warehouse_id(createOrderCommand.getWarehouse_id())
                .payment_method(createOrderCommand.getPayment_method())
                .build();

        List<OrderItem> orderItems = createOrderCommand.getOrders().stream()
                .map(this::createOrderItem)
                .toList();

        order.setOrders(orderItems);

        return order;
    }
    public OrderItem createOrderItem(CreateOrderItemCommand createOrderItemCommand) {
        return OrderItem.builder()
                .order_id(createOrderItemCommand.getOrder_id())
                .product_id(createOrderItemCommand.getProduct_id())
                .quantity(createOrderItemCommand.getQuantity())
                .price(createOrderItemCommand.getPrice())
                .color_id(createOrderItemCommand.getColor_id())
                .size_id(createOrderItemCommand.getSize_id())
                .build();
    }

    public CreateOrderResponse orderResponse (Order order) {
        return CreateOrderResponse.builder()
                .order_id(order.getId())
               .user_id(order.getUser_id())
               .order_date(order.getOrder_date())
               .build();
    }

}
