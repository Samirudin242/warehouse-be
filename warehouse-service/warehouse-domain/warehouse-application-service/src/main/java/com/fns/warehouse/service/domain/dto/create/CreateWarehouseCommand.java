package com.fns.warehouse.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@Data
public class CreateWarehouseCommand {
    private final String name;
    private final UUID admin_id;
}
