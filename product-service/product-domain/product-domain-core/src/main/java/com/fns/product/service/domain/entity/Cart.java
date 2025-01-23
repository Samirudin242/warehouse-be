package com.fns.product.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cart {
    private UUID id;
    private ProductColors selected_color;
    private ProductSizes selected_size;
    private UUID user_id;
    private Product product;
    private Integer quantity;
    private Double price;
}
