package com.fns.user.service.dataaccess.user.repository;

import com.fns.user.service.dataaccess.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

        @Query("SELECT u FROM UserEntity u WHERE u.user_name = :usernameOrEmail OR u.email = :usernameOrEmail")
        UserEntity findByUsernameOrEmail(String usernameOrEmail);

        @Query("SELECT u FROM UserEntity u WHERE u.user_name = :user_name OR u.email = :email")
        Optional<UserEntity> findByUsernameOrEmail(String user_name, String email);

        Page<UserEntity> findAll(Pageable pageable);


}
