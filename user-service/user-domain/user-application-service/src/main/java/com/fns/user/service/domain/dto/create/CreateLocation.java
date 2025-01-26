package com.fns.user.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@Data
public class CreateLocation {
    private String address;
    private String province;
    private String city;
    private String province_id;
    private String city_id;
    private String postal_code;
    private String phone_number;
    private UUID user_id;
}
