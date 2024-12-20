package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductBrandEntity;
import com.fns.product.service.dataaccess.mapper.ProductBrandMapper;
import com.fns.product.service.dataaccess.repository.ProductBrandJpaRepository;
import com.fns.product.service.domain.entity.ProductBrand;
import com.fns.product.service.domain.ports.output.repository.ProductBrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class ProductBrandImpl implements ProductBrandRepository {

    private final ProductBrandJpaRepository productBrandJpaRepository;
    private final ProductBrandMapper productBrandMapper;

    public ProductBrandImpl(ProductBrandJpaRepository productBrandJpaRepository, ProductBrandMapper productBrandMapper) {
        this.productBrandJpaRepository = productBrandJpaRepository;
        this.productBrandMapper = productBrandMapper;
    }

    @Override
    public Optional<ProductBrand> findById(UUID brandId) {
        Optional<ProductBrandEntity> optionalProductBrandEntity = productBrandJpaRepository.findById(brandId);

        // Handle entity mapping
        return optionalProductBrandEntity.map(productBrandMapper::brandFromBrandEntity);
    }
}
