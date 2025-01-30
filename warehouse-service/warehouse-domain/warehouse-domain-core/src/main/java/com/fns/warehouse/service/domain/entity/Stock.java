package com.fns.warehouse.service.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Data
public class Stock {
    private UUID id;
    private UUID product_id;
    private UUID warehouse_id;
    private Integer quantity;
}
