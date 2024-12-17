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
@Table(name = "product_sizes")
@Entity
public class ProductSizes {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String size;

    private Boolean is_stock;

}
