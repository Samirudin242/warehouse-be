package com.fns.warehouse.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_user")
@Entity
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private Date order_date;
    private Double total_amount;
    private Double total_shipping;
    private String user_address;
    private Double user_latitude;
    private Double user_longitude;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private WarehouseEntity warehouse;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PaymentEntity payment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();


    public enum OrderStatus {
        PAYMENT_WAITING,
        PAYMENT_COMPLETED,
        SHIPPING,
        COMPLETED
    }

}

