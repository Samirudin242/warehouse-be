package com.fns.warehouse.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@Data
public class CreateWarehouseResponse {
    private final UUID id;
    private final String name;
    private final UUID admin_id;
}
