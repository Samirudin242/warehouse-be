package com.fns.warehouse.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class GetAllWarehouseResponse {
    private final UUID id;
    private final String name;
    private final String location;
    private final String province;
    private final String city;
}
