package com.fns.user.service.domain;

import com.fns.user.service.domain.dto.create.CreateLocation;
import com.fns.user.service.domain.dto.get.LocationResponse;
import com.fns.user.service.domain.entity.Location;
import com.fns.user.service.domain.mapper.LocationDataMapper;
import com.fns.user.service.domain.mapper.UserDataMapper;
import com.fns.user.service.domain.ports.output.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class LocationCommandHandler {
    private final LocationRepository locationRepository;
    private final UserDataMapper userDataMapper;
    private final LocationDataMapper locationDataMapper;

    public LocationCommandHandler(LocationRepository locationRepository, UserDataMapper userDataMapper, LocationDataMapper locationDataMapper) {
        this.locationRepository = locationRepository;
        this.userDataMapper = userDataMapper;
        this.locationDataMapper = locationDataMapper;
    }

    public List<LocationResponse> getAllLocation(UUID userId) {
        List<Location> getAll = getLocations(userId);

        return userDataMapper.getLocationResponse(getAll);
    }

    public LocationResponse createLocation(CreateLocation createLocation) {
        Location location = locationDataMapper.locationFromLocationResponse(createLocation);

        Location saved = savedLocation(location);

        return locationDataMapper.locationToLocationResponse(saved);
    }

    private Location savedLocation(Location location) {
        try {
            return locationRepository.saveLocation(location);
        } catch (Exception e) {
            throw new RuntimeException("Error getting locations for user");
        }
    }

    public List<Location> getLocations(UUID userId) {
        try {
            return locationRepository.getLocations(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error getting locations for user");
        }
    }
}
