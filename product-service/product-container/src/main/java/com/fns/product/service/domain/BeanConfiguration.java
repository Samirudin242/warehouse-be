package com.fns.product.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductDomainService userDomainService() {
        return new ProductDomainServiceImpl();
    }
}
