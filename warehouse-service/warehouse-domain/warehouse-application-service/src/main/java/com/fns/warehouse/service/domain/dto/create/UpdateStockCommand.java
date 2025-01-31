package com.fns.warehouse.service.domain.dto.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class UpdateStockCommand {
    private UUID productId;
    private UUID warehouseId;
    private UUID fromWarehouseId;
    private Integer quantity;
    private UUID orderId;
    private Double price;
    private Boolean isTransferStock;
}
