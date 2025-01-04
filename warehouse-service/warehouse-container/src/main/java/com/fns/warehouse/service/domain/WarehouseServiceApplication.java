package com.fns.warehouse.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = { "com.fns.warehouse.service.dataaccess", "com.fns.warehouse.service.domain.ports.output.repository" })
@EntityScan(basePackages = { "com.fns.warehouse.service.dataaccess", "com.fns.warehouse.service.messaging" })
@SpringBootApplication(scanBasePackages = "com.fns")
@ComponentScan(basePackages = {"com.fns.warehouse.service", "com.fns.warehouse.service.rest"})
public class WarehouseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarehouseServiceApplication.class, args);
    }
}
