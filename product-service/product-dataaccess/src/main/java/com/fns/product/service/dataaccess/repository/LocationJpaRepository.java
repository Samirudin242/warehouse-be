package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationJpaRepository extends JpaRepository<LocationEntity, UUID> {
}
