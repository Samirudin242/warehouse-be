package com.fns.warehouse.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_user_items")
@Entity
public class OrderItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private Integer quantity;

    private Double price;

    @Column(name = "warehouse_id", columnDefinition = "UUID[]")
    private List<UUID> warehouse_id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private ProductColorEntity productColor;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private ProductSizeEntity productSize;

}
