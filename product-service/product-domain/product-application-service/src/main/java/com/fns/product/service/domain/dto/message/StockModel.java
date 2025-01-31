package com.fns.product.service.domain.dto.message;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
public class StockModel {
    private UUID id;
    private Integer quantity;
    private UUID product_id;
    private UUID warehouse_id;
}
