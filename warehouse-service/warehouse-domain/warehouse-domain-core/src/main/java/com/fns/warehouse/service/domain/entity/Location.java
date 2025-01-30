package com.fns.warehouse.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class Location {
    private UUID id;
    private String address;
    private String province;
    private String province_id;
    private String city_id;
    private String city;
    private String postal_code;
    private UUID warehouse_id;
    private Double latitude;
    private Double longitude;
}
