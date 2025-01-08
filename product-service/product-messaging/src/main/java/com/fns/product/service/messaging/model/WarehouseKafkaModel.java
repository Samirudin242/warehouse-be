package com.fns.product.service.messaging.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@Builder
public class WarehouseKafkaModel {

    private final UUID id;
    private final UUID admin_id;
    private final UUID location_id;
    private final String name;
    private final String address;
    private final String city;
    private final String city_id;
    private final String province;
    private final String province_id;
    private final String postal_code;
}
