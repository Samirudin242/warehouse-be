package com.fns.warehouse.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@Data
public class CreateOrderItemCommand {
    private UUID id;
    private UUID order_id;
    private UUID product_id;
    private Integer quantity;
    private Double price;
    private UUID size_id;
    private UUID color_id;
    private List<UUID> warehouse_id;
}
