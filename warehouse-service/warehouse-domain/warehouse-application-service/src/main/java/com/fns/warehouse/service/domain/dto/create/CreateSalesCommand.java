package com.fns.warehouse.service.domain.dto.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CreateSalesCommand {
    private UUID order_item_id;
    private Double total_price;
    private Integer quantity;
    private Date transaction_date;
    private UUID product_id;
    private UUID warehouse_id;
}
