package com.fns.product.service.domain.dto.message;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class WarehouseModel {
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
