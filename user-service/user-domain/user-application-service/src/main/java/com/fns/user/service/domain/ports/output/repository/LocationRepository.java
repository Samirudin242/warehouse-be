package com.fns.user.service.domain.ports.output.repository;

import com.fns.user.service.domain.entity.Location;

public interface LocationRepository {
    Location saveLocation(Location location);
}
