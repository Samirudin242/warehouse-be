package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.dataaccess.warehouse.adapter.WarehouseRepositoryImpl;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {

    @Bean
    public WarehouseDomainService warehouseDomainService() {
        return new WarehouseDomainServiceImpl();
    }

}