package com.fns.user.service.domain.ports.output.repository;

import com.fns.user.service.domain.entity.Location;

import java.util.List;
import java.util.UUID;

public interface LocationRepository {
    Location saveLocation(Location location);

    List<Location> getLocations(UUID userId);
}
