package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductAndSizeEntity;
import com.fns.product.service.dataaccess.mapper.ProductAndSizeMapper;
import com.fns.product.service.dataaccess.repository.ProductAndSizeJpaRepository;
import com.fns.product.service.domain.entity.ProductAndSize;
import com.fns.product.service.domain.ports.output.repository.ProductAndSizeRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductAndSizeImpl implements ProductAndSizeRepository {
    private final ProductAndSizeJpaRepository productAndSizeJpaRepository;
    private final ProductAndSizeMapper productAndSizeMapper;

    public ProductAndSizeImpl(ProductAndSizeJpaRepository productAndSizeJpaRepository, ProductAndSizeMapper productAndSizeMapper) {
        this.productAndSizeJpaRepository = productAndSizeJpaRepository;
        this.productAndSizeMapper = productAndSizeMapper;
    }

    @Override
    public void save(ProductAndSize productAndSize) {
        ProductAndSizeEntity entity = productAndSizeMapper.productAndSizeEntity(productAndSize);
        productAndSizeJpaRepository.save(entity);
    }
}
