package com.fns.user.service.dataaccess.user.repository;

import com.fns.user.service.dataaccess.user.entity.UserEntity;
import com.fns.user.service.domain.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

        @Query("SELECT u FROM UserEntity u WHERE u.status = :status AND u.user_role.role_name = 'CUSTOMER'")
        List<UserEntity> findActiveCustomers(Status status);

        @Query("SELECT COUNT(u) FROM UserEntity u WHERE u.createdAt BETWEEN :start AND :end AND u.status = :status AND u.user_role.role_name = 'CUSTOMER'")
        Long countActiveCustomersCreatedBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, Status status);

        @Query("""
       SELECT u FROM UserEntity u
       WHERE (u.user_role.id = :role_id)
       AND (u.name LIKE CONCAT('%', :name, '%'))
       """)
        Page<UserEntity> findByRoleIdAndName(UUID role_id, String name, Pageable pageable);

        @Query("""
       SELECT u FROM UserEntity u
       WHERE u.user_role.id = :role_id
       """)
        Page<UserEntity> findByRoleId(UUID role_id, Pageable pageable);

        @Query("SELECT u FROM UserEntity u WHERE u.user_name = :usernameOrEmail OR u.email = :usernameOrEmail")
        UserEntity findByUsernameOrEmail(String usernameOrEmail);

        @Query("SELECT u FROM UserEntity u WHERE u.user_name = :user_name OR u.email = :email")
        Optional<UserEntity> findByUsernameOrEmail(String user_name, String email);
        Page<UserEntity> findAll(Pageable pageable);

}
