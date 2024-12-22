package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.ProductImagesEntity;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.domain.entity.ProductImages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProductImageMapper {

    @Autowired
    private ProductJpaRepository productJpaRepository;


    public ProductImagesEntity imageToImageEntity(ProductImages productImages) {

        ProductEntity productEntity = getProductById(productImages.getProduct_id());

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

    public List<ProductImages> imagesFromImageEntity(List<ProductImagesEntity> productImagesEntities) {
        return productImagesEntities.stream()
                .filter(entity -> {
                    log.info("Processing entity with Image URL: {}", entity.getImage_url());
                    if (entity.getImage_url() == null || entity.getImage_url().trim().isEmpty()) {
                        log.warn("Skipping entity with null or empty image_url: {}", entity);
                        return false;
                    }
                    if (entity.getProduct() == null) {
                        log.warn("Skipping entity with null product reference: {}", entity);
                        return false;
                    }
                    return true;
                })
                .map(entity -> {
                    try {
                        ProductImages productImage = new ProductImages(
                                entity.getId(),
                                entity.getProduct().getId(),
                                entity.getImage_url()
                        );
                        log.info("product id image url {}", entity.getProduct().getId());
                        log.info("Successfully built ProductImages: {}", productImage);
                        return productImage;
                    } catch (Exception e) {
                        log.error("Error creating ProductImages for entity: {}", entity, e);
                        throw e; // Re-throw the exception after logging
                    }
                })
                .collect(Collectors.toList());
    }


    private ProductEntity getProductById(UUID id) {
        return productJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }
}
