package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.entity.LocationEntity;
import com.fns.warehouse.service.dataaccess.mapper.LocationDataAccessMapper;
import com.fns.warehouse.service.dataaccess.repository.LocationJpaRepository;
import com.fns.warehouse.service.domain.entity.Location;
import com.fns.warehouse.service.domain.ports.output.repository.LocationRepository;
import org.springframework.stereotype.Component;

@Component
public class LocationRepositoryImpl implements LocationRepository {

    private final LocationDataAccessMapper locationDataAccessMapper;
    private final LocationJpaRepository locationJpaRepository;

    public LocationRepositoryImpl(LocationDataAccessMapper locationDataAccessMapper, LocationJpaRepository locationJpaRepository) {
        this.locationDataAccessMapper = locationDataAccessMapper;
        this.locationJpaRepository = locationJpaRepository;
    }

    @Override
    public Location savedLocaion(Location location) {
        LocationEntity savedLocation = locationJpaRepository.save(locationDataAccessMapper.locationToLocationEntity(location));
        return locationDataAccessMapper.locationEntityToLocation(savedLocation);
    }
}
