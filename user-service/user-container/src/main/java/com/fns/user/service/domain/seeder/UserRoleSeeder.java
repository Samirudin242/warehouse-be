package com.fns.user.service.domain.seeder;

import com.fns.user.service.dataaccess.user.entity.UserRoleEntity;
import com.fns.user.service.dataaccess.user.repository.UserRoleJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class UserRoleSeeder implements CommandLineRunner {
    private final UserRoleJpaRepository userRoleJpaRepository;

    public UserRoleSeeder(UserRoleJpaRepository userRoleJpaRepository) {
        this.userRoleJpaRepository = userRoleJpaRepository;
    }

    @Override
    public void run(String... args) {
        if (userRoleJpaRepository.count() == 0) {
            List<UserRoleEntity> roles = Arrays.asList(
                    UserRoleEntity.builder()
                            .id(UUID.randomUUID())
                            .role_name("WAREHOUSE_ADMIN")
                            .build(),
                    UserRoleEntity.builder()
                            .id(UUID.randomUUID())
                            .role_name("CUSTOMER")
                            .build(),
                    UserRoleEntity.builder()
                            .id(UUID.randomUUID())
                            .role_name("SUPER_ADMIN")
                            .build()
            );

            userRoleJpaRepository.saveAll(roles);

            System.out.println("User roles seeded successfully.");
        } else {
            System.out.println("User roles already exist. Skipping seeding.");
        }
    }
}