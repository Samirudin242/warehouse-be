package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductSizesEntity;
import com.fns.product.service.dataaccess.mapper.ProductSizeMapper;
import com.fns.product.service.dataaccess.repository.ProductSizeJpaRepository;
import com.fns.product.service.domain.entity.ProductSizes;
import com.fns.product.service.domain.ports.output.repository.ProductSizesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class ProductSizesImpl implements ProductSizesRepository {

    private final ProductSizeJpaRepository productSizesJpaRepository;
    private final ProductSizeMapper productSizeMapper;

    public ProductSizesImpl(ProductSizeJpaRepository productSizesJpaRepository, ProductSizeMapper productSizeMapper) {
        this.productSizesJpaRepository = productSizesJpaRepository;
        this.productSizeMapper = productSizeMapper;
    }

    @Override
    public Optional<ProductSizes> findById(UUID sizeId) {
        Optional<ProductSizesEntity> optionalProductSizesEntity = productSizesJpaRepository.findById(sizeId);

        return optionalProductSizesEntity.map(productSizeMapper::sizeFromSizeEntity);
    }
}
