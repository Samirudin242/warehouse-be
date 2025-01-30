package com.fns.user.service.domain.mapper;

import com.fns.user.service.domain.dto.create.CreateLocation;
import com.fns.user.service.domain.dto.get.LocationResponse;
import com.fns.user.service.domain.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationDataMapper {

    public Location locationFromLocationResponse(CreateLocation createLocation) {
        return Location.builder()
                .province(createLocation.getProvince())
                .province_id(createLocation.getProvince_id())
                .city(createLocation.getCity())
                .city_id(createLocation.getCity_id())
                .address(createLocation.getAddress())
                .postal_code(createLocation.getPostal_code())
                .phone_number(createLocation.getPhone_number())
                .user_id(createLocation.getUser_id())
                .latitude(createLocation.getLatitude())
                .longitude(createLocation.getLongitude())
                .build();
    }

    public LocationResponse locationToLocationResponse(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .province(location.getProvince())
                .provinceId(location.getProvince_id())
                .city(location.getCity())
                .cityId(location.getCity_id())
                .address(location.getAddress())
                .postal_code(location.getPostal_code())
                .name(location.getName())
                .phone_number(location.getPhone_number())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }

}
