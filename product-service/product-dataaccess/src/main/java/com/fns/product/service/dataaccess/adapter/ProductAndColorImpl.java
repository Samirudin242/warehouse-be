package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductAndColorEntity;
import com.fns.product.service.dataaccess.mapper.ProductAndColorMapper;
import com.fns.product.service.dataaccess.repository.ProductAndColorJpaRepository;
import com.fns.product.service.domain.entity.ProductAndColor;
import com.fns.product.service.domain.ports.output.repository.ProductAndColorRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductAndColorImpl implements ProductAndColorRepository {

    private final ProductAndColorJpaRepository productAndColorRepository;
    private final ProductAndColorMapper productAndColorMapper;

    public ProductAndColorImpl(ProductAndColorJpaRepository productAndColorRepository, ProductAndColorMapper productAndColorMapper) {
        this.productAndColorRepository = productAndColorRepository;
        this.productAndColorMapper = productAndColorMapper;
    }

    @Override
    public void save(ProductAndColor productAndColor) {
        ProductAndColorEntity product = productAndColorMapper.productAndColorEntity(productAndColor);
        productAndColorRepository.save(product);
    }
}
