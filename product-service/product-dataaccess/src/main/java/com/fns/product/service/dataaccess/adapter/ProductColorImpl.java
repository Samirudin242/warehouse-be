package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductColorsEntity;
import com.fns.product.service.dataaccess.mapper.ProductColorMapper;
import com.fns.product.service.dataaccess.repository.ProductColorsJpaRepository;
import com.fns.product.service.domain.entity.ProductColors;
import com.fns.product.service.domain.ports.output.repository.ProductColorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
@Slf4j
@Component
public class ProductColorImpl implements ProductColorsRepository {

    private final ProductColorsJpaRepository productColorsJpaRepository;
    private final ProductColorMapper productColorMapper;

    public ProductColorImpl(ProductColorsJpaRepository productColorsRepository, ProductColorMapper productColorMapper) {
        this.productColorsJpaRepository = productColorsRepository;
        this.productColorMapper = productColorMapper;
    }


    @Override
    public Optional<ProductColors> findById(UUID colorId) {
        Optional<ProductColorsEntity> optionalProductColorsEntity = productColorsJpaRepository.findById(colorId);
        return optionalProductColorsEntity.map(productColorMapper::colorFromColorEntity);
    }
}
