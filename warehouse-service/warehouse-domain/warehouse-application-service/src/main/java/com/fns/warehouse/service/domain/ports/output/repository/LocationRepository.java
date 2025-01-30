package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.entity.Location;

public interface LocationRepository {
    Location savedLocation(Location location);
}
