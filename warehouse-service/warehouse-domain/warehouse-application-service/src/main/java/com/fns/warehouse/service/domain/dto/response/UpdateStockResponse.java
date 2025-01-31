package com.fns.warehouse.service.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class UpdateStockResponse {
    private String message;
    private UUID stock_id;
    private UUID warehouseId;
    private UUID productId;
    private Integer updateStock;
    private Boolean isUpdateAnotherStock;
    private Integer updateStockLeft;
}
