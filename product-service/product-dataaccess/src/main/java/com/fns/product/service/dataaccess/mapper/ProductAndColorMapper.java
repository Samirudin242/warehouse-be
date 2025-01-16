package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductAndColorEntity;
import com.fns.product.service.dataaccess.entity.ProductColorsEntity;
import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.ProductSizesEntity;
import com.fns.product.service.dataaccess.repository.ProductColorsJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.domain.entity.ProductAndColor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductAndColorMapper {

    @Autowired
    private ProductJpaRepository productJpaRepository;
    @Autowired
    private ProductColorsJpaRepository productColorsJpaRepository;


    public ProductAndColorEntity productAndColorEntity(ProductAndColor productAndColor) {
        ProductEntity productEntity = getProductById(productAndColor.getProduct_id());
        ProductColorsEntity productColorsEntity = getProductColorById(productAndColor.getColor_id());

        return ProductAndColorEntity.builder()
                .product(productEntity)
                .color(productColorsEntity)
                .build();
    }


    private ProductEntity getProductById(UUID id) {
        return productJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    private ProductColorsEntity getProductColorById(UUID id) {
        return productColorsJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product color not found with id: " + id));
    }


}
