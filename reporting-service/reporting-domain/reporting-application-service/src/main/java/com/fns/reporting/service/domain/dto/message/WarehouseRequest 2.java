package com.fns.reporting.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class WarehouseRequest {
    private final String id;

    private final String sagaId;

    private final String warehouseId;

    private final Instant createdAt;

    private final List<String> failureMessage;
}
