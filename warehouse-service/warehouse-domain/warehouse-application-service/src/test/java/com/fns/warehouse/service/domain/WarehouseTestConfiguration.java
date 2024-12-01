package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.ports.output.message.publisher.StockRequestRequestMessagePublisher;
import com.fns.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedRequestMessagePublisher;
import com.fns.warehouse.service.domain.ports.output.repository.StockRepository;
import com.fns.warehouse.service.domain.ports.output.repository.UserRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.fns.warehouse.service.domain")
public class WarehouseTestConfiguration {

    @Bean
    public WarehouseCreatedRequestMessagePublisher warehouseCreatedRequestMessagePublisher() {
        return Mockito.mock(WarehouseCreatedRequestMessagePublisher.class);
    }

    @Bean
    public StockRequestRequestMessagePublisher stockRequestRequestMessagePublisher() {
        return Mockito.mock(StockRequestRequestMessagePublisher.class);
    }

    @Bean
    public WarehouseRepository warehouseRepository() {
        return Mockito.mock(WarehouseRepository.class);
    }

    @Bean
    public StockRepository stockRepository() {
        return Mockito.mock(StockRepository.class);
    }

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public WarehouseDomainService warehouseDomainService() {
        return new WarehouseDomainServiceImpl();
    }
}
