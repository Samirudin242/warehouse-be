package com.fns.product.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CreateCartResponse {
    private UUID id;
    private UUID selected_color;
    private UUID selected_size;
    private UUID user_id;
    private UUID product_id;
    private Integer quantity;
    private Double price;
}
