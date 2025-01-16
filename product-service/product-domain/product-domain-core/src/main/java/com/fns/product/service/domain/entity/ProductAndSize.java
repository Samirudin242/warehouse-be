package com.fns.product.service.domain.entity;

import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@Builder
public class ProductAndSize {
    private UUID id;
    private UUID product_id;
    private UUID size_id;
}
