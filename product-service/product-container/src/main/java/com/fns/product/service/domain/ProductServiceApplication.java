package com.fns.product.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.fns.product.service.dataaccess", "com.fns.dataaccess", "com.fns.product.service.domain.ports.output.repository" })
@EntityScan(basePackages = { "com.fns.product.service.dataaccess", "com.fns.product.service.dataaccess.entity", "com.fns.product.dataaccess", "com.fns.product.service.messaging","com.fns.product.messaging" })
@SpringBootApplication(scanBasePackages = "com.fns")
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
