package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductAndSizeEntity;
import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.ProductSizesEntity;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductSizeJpaRepository;
import com.fns.product.service.domain.entity.ProductAndSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductAndSizeMapper {

    @Autowired
    private ProductJpaRepository productJpaRepository;
    @Autowired
    private ProductSizeJpaRepository productSizeJpaRepository;

    public ProductAndSizeEntity productAndSizeEntity(ProductAndSize productAndSize) {
        ProductSizesEntity productSizeEntity = getProductSizeById(productAndSize.getSize_id());
        ProductEntity productEntity = getProductById(productAndSize.getProduct_id());

        return ProductAndSizeEntity.builder()
                .size(productSizeEntity)
                .product(productEntity)
                .build();
    }

    private ProductEntity getProductById(UUID id) {
        return productJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    private ProductSizesEntity getProductSizeById(UUID id) {
        return productSizeJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product size not found with id: " + id));
    }


}
