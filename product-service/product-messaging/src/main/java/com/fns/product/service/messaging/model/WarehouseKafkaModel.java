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
    private final UUID adminId;
    private final UUID locationId;
    private final String name;
    private final String address;
    private final String city;
    private final String cityId;
    private final String province;
    private final String provinceId;
    private final String postalCode;
    private final Double latitude;
    private final Double longitude;
}
