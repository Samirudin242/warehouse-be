package com.fns.product.service.domain.entity;

import lombok.*;

import java.util.UUID;

// Entity: ProductPrices
@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductPrices {
    private final UUID id;
    private final UUID productId;
    private String currency;
    private Double price;
    private Double discountedValue;
    private Boolean onSales;
}
