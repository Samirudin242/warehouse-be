package com.fns.user.service.domain.dto.get;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class LocationResponse {
    private UUID id;
    private String province;
    private String provinceId;
    private String city;
    private String cityId;
    private String address;
    private String postal_code;
    private String name;
    private String phone_number;
}
