package com.fns.product.service.messaging.model;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
public class StockKafkaModel {
    private UUID id;
    private Integer quantity;
    private UUID product_id;
    private UUID warehouse_id;
}
