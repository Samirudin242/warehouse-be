package com.fns.user.service.dataaccess.user.repository;

import com.fns.user.service.dataaccess.user.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRoleJpaRepository extends JpaRepository<UserRoleEntity, UUID> {
}
