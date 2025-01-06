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
    private final String address;
    private final String city_id;
    private final String city;
    private final String province_id;
    private final String province;
    private final String postal_code;
}
