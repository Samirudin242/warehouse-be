package com.fns.product.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_prices")
@Entity
public class ProductPricesEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String currency;

    private Double price;

    private Double discounted_value;

    private Boolean on_sales;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;
}


