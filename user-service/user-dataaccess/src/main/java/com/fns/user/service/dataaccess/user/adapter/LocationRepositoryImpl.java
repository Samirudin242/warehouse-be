package com.fns.user.service.dataaccess.user.adapter;

import com.fns.user.service.dataaccess.user.entity.LocationEntity;
import com.fns.user.service.dataaccess.user.mapper.LocationDataAccessMapper;
import com.fns.user.service.dataaccess.user.repository.LocationJpaRepository;
import com.fns.user.service.domain.entity.Location;
import com.fns.user.service.domain.ports.output.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LocationRepositoryImpl implements LocationRepository {

    private final LocationJpaRepository locationJpaRepository;
    private final LocationDataAccessMapper locationDataAccessMapper;

    public LocationRepositoryImpl(LocationJpaRepository locationRepository, LocationDataAccessMapper locationDataAccessMapper) {
        this.locationJpaRepository = locationRepository;
        this.locationDataAccessMapper = locationDataAccessMapper;
    }



    @Override
    public Location saveLocation(Location location) {
        LocationEntity locationEntity = locationDataAccessMapper.locationToLocationEntity(location);

        LocationEntity savedLocationEntity = locationJpaRepository.save(locationEntity);

        return locationDataAccessMapper.locationEntityToLocation(savedLocationEntity);

    }
}
