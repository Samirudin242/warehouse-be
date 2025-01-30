package com.fns.user.service.dataaccess.user.repository;

import com.fns.user.service.dataaccess.user.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LocationJpaRepository extends JpaRepository<LocationEntity, UUID> {

    List<LocationEntity> findByUsers_Id(UUID userId);

}
