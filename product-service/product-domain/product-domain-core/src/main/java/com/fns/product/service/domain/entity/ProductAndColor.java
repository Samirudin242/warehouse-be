package com.fns.product.service.domain.entity;

import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@Builder
public class ProductAndColor {
    private UUID id;
    private UUID product_id;
    private UUID color_id;
    private String color;
}
