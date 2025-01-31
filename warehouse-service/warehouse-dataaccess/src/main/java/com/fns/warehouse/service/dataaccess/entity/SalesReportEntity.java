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
@Table(name = "sales_report")
@Entity
public class SalesReportEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private Integer quantity;

    private Double totalPrice;

    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private WarehouseEntity warehouses;

    @OneToOne
    @JoinColumn(name = "order_item_id", referencedColumnName = "id")
    private OrderItemEntity orderItem;


}
