package com.fns.user.service.domainn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.fns.user.service.dataaccess", "com.fns.dataaccess", "com.fns.user.service.domain.ports.output.repository" })
@EntityScan(basePackages = { "com.fns.user.service.dataaccess", "com.fns.user.dataaccess", "com.fns.user.service.messaging","com.fns.user.messaging" })
@SpringBootApplication(scanBasePackages = "com.fns")
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
