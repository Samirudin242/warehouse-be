package com.fns.warehouse.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
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
    private Boolean isVerified;
}
