package com.fns.warehouse.service.domain.dto.get;

import com.fns.warehouse.service.domain.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class GetOrderResponse {
    private UUID id;
    private Date order_date;
    private Double total_amount;
    private Double total_shipping;
    private String user_address;
    private Double user_latitude;
    private Double user_longitude;
    private OrderStatus status;
    private PaymentResponse payment;
    private List<OrderItemResponse> order_items;

}
