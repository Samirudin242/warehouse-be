package com.fns.user.service.dataaccess.user.mapper;

import com.fns.user.service.dataaccess.user.entity.LocationEntity;
import com.fns.user.service.dataaccess.user.entity.UserEntity;
import com.fns.user.service.dataaccess.user.repository.UserJpaRepository;
import com.fns.user.service.domain.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocationDataAccessMapper {

    @Autowired
    private UserJpaRepository userJpaRepository;


    public LocationEntity locationToLocationEntity(Location location) {


        UserEntity userEntity = getUser(location.getUser_id());

        // Map the Location to LocationEntity
        return LocationEntity.builder()
                .address(location.getAddress())
                .province(location.getProvince())
                .city(location.getCity())
                .province_id(location.getProvince_id())
                .city_id(location.getCity_id())
                .postal_code(location.getPostal_code())
                .users(userEntity)
                .phone_number(location.getPhone_number())
                .build();
    }

    private UserEntity getUser(UUID id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    public Location locationEntityToLocation(LocationEntity locationEntity) {
        return Location.builder()
                .id(locationEntity.getId())
                .user_id(locationEntity.getUsers().getId())
                .address(locationEntity.getAddress())
                .province(locationEntity.getProvince())
                .city(locationEntity.getCity())
                .province_id(locationEntity.getProvince_id())
                .city_id(locationEntity.getCity_id())
                .postal_code(locationEntity.getPostal_code())
                .name(locationEntity.getUsers().getName())
                .phone_number(
                        (locationEntity.getPhone_number() == null || locationEntity.getPhone_number().isEmpty())
                                ? locationEntity.getUsers().getPhone_number()
                                : locationEntity.getPhone_number()
                )
                .build();
    }


}
