package com.fns.warehouse.service.messaging.model;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
public class WarehouseKafkaModel {
    UUID id;
    UUID adminId;
    UUID locationId;
    String name;
    String address;
    String city;
    String cityId;
    String province;
    String provinceId;
    String postalCode;
}
