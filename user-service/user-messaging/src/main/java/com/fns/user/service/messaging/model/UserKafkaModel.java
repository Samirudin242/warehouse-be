package com.fns.user.service.messaging.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKafkaModel {
    private UUID id;
    private String name;
    private String userName;
    private String email;
    private String profilePicture;
    private String roleId;
    private String phoneNumber;
    private String address;
    private String province;
    private String city;
    private String provinceId;
    private String cityId;
    private String postalCode;
    private boolean isVerified;
}
