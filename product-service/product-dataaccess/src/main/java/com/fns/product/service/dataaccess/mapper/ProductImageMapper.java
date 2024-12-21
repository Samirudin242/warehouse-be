package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.ProductImagesEntity;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.domain.entity.ProductImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductImageMapper {

    @Autowired
    private ProductJpaRepository productJpaRepository;


    public ProductImagesEntity imageToImageEntity(ProductImages productImages) {

        ProductEntity productEntity = getProductById(productImages.getProductId());

        // Map the ProductImage to ProductImageEntity
        return ProductImagesEntity.builder()
                .id(productImages.getId())
                .image_url(productImages.getImageUrl())
                .product(productEntity)
                .build();
    }

    public ProductImages imageFromImageEntity(ProductImagesEntity productImageEntity) {

        return ProductImages.builder()
                .id(productImageEntity.getId())
                .imageUrl(productImageEntity.getImage_url())
                .productId(productImageEntity.getProduct().getId())
                .build();
    }

    private ProductEntity getProductById(UUID id) {
        return productJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }
}
