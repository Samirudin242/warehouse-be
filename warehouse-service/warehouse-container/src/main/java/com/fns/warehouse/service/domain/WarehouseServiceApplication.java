package com.fns.warehouse.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaRepositories(basePackages = {
		"com.fns.warehouse.service.dataaccess.warehouse.repository",
})
@EntityScan(basePackages = {
		"com.fns.warehouse.service.dataaccess.warehouse.entity",
		"com.fns.warehouse.service.domain.ports.output.message.publisher"
})
@SpringBootApplication(scanBasePackages = "com.fns")
public class WarehouseServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WarehouseServiceApplication.class, args);
	}

	@GetMapping
	public String hello(){
		return  "Hello World ifhjdhjkfd";
	}

	@GetMapping("/world")
	public String world(){
		return  "Hello World kfs;";
	}

}
