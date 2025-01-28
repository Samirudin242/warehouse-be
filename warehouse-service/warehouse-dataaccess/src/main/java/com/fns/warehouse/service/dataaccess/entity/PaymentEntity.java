package com.fns.warehouse.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
@Entity
public class PaymentEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private PaymentType payment_type;

    private Date payment_date;

    private String payment_proof;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    public enum PaymentType {
        CREDIT_CARD,
        CASH,
        ONLINE_TRANSFER,
        PAYPAL,
        BANK_TRANSFER,
        OTHER
    }



}
