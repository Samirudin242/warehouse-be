package com.fns.warehouse.service.domain.dto.get;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class GetNearestWarehouseResponse {
    private UUID id;
    private String name;
    private String address;
    private String province_id;
    private String city_id;
    private Double latitude;
    private Double longitude;
}
