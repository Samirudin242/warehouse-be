package com.fns.warehouse.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class Warehouse {

    private final UUID id;
    private final String name;
    private final UUID admin_id;
    private final String address;
    private final String city_id;
    private final String province_id;
}
