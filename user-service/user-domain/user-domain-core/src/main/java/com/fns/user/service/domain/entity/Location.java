package com.fns.user.service.domain.entity;


import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Location {

    private UUID id;
    private UUID user_id;
    private String province;
    private String city;
    private String address;
    private String postal_code;
    private String province_id;
    private String city_id;
    private String name;
    private String phone_number;
    private Double latitude;
    private Double longitude;
}
