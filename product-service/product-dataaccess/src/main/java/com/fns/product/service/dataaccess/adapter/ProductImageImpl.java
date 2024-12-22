package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductImagesEntity;
import com.fns.product.service.dataaccess.mapper.ProductImageMapper;
import com.fns.product.service.dataaccess.repository.ProductImageUrlJpaRepository;
import com.fns.product.service.domain.entity.ProductImages;
import com.fns.product.service.domain.ports.output.repository.ProductImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductImageImpl implements ProductImageRepository {

    private final ProductImageMapper productImageMapper;
    private final ProductImageUrlJpaRepository productImageUrlJpaRepository;

    public ProductImageImpl(ProductImageMapper productImageMapper, ProductImageUrlJpaRepository productImageUrlJpaRepository) {
        this.productImageMapper = productImageMapper;
        this.productImageUrlJpaRepository = productImageUrlJpaRepository;
    }

    @Override
    public ProductImages saveProductImages(ProductImages productImages) {
        ProductImagesEntity productImagesEntity = productImageMapper.imageToImageEntity(productImages);
        log.info("Saving product image: {}", productImagesEntity);
        ProductImagesEntity savedProductImagesEntity = productImageUrlJpaRepository.save(productImagesEntity);
        return productImageMapper.imageFromImageEntity(savedProductImagesEntity);
    }

    @Override
    public List<ProductImages> findByProductId(UUID productId) {
        List<ProductImagesEntity> productImagesEntities = productImageUrlJpaRepository.findByProductId(productId);
        log.info("List product images found: {}", productImagesEntities);
        return productImageMapper.imagesFromImageEntity(productImagesEntities);
    }

}
