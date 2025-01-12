package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.entity.ProductEntity;
import com.fns.warehouse.service.dataaccess.mapper.ProductDataAccessMapper;
import com.fns.warehouse.service.dataaccess.repository.ProductJpaRepository;
import com.fns.warehouse.service.domain.entity.Product;
import com.fns.warehouse.service.domain.ports.output.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductDataAccessMapper productDataAccessMapper;

    public ProductRepositoryImpl(ProductJpaRepository productJpaRepository, ProductDataAccessMapper productDataAccessMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productDataAccessMapper = productDataAccessMapper;
    }

    @Override
    public void save(Product product) {

        log.info("Received product to save: {}", product);

        ProductEntity entity = productDataAccessMapper.productToProductEntity(product);

        log.info("Saving product to database: {}", entity);

        ProductEntity saved =  productJpaRepository.save(entity);

        log.info("Saved product<<<<<<<<<<<<: {}", saved);
    }
}
