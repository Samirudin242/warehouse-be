package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.repository.ProductCategoriesJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductColorsJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductSizeJpaRepository;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.dataaccess.entity.ProductBrandEntity;
import com.fns.product.service.dataaccess.entity.ProductCategoriesEntity;
import com.fns.product.service.dataaccess.entity.ProductSizesEntity;
import com.fns.product.service.dataaccess.entity.ProductColorsEntity;
import com.fns.product.service.dataaccess.repository.ProductBrandJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ProductMapper {

    @Autowired
    private ProductBrandJpaRepository productBrandJpaRepository;

    @Autowired
    private ProductCategoriesJpaRepository productCategoriesJpaRepository;

    @Autowired
    private ProductSizeJpaRepository productSizesRepository;

    @Autowired
    private ProductColorsJpaRepository productColorsJpaRepository;

    public ProductEntity productTotProductEntity(Product product) {
        ProductBrandEntity productBrand = getProductBrandById(product.getBrandId());
        ProductCategoriesEntity productCategory = getProductCategoryById(product.getProductCategoryId());

        return ProductEntity.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .slug(product.getSlug())
                .gender(product.getGender())
                .description(product.getDescription())
                .brand(productBrand)
                .category(productCategory)
//                .productAndSize(productSize)
                .build();
    }

    public Product productFromProductEntity(ProductEntity productEntity) {
        return Product.builder()
               .id(productEntity.getId())
               .sku(productEntity.getSku())
               .name(productEntity.getName())
               .slug(productEntity.getSlug())
                .gender(productEntity.getGender())
                .description(productEntity.getDescription())
               .brandId(productEntity.getBrand().getId())
               .productCategoryId(productEntity.getCategory().getId())
               .build();
    }

    private ProductBrandEntity getProductBrandById(UUID brandId) {
        return productBrandJpaRepository.findById(brandId)
                .orElseThrow(() -> new IllegalArgumentException("Product brand not found with id: " + brandId));
    }

    private ProductCategoriesEntity getProductCategoryById(UUID categoryId) {
        return productCategoriesJpaRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Product category not found with id: " + categoryId));
    }

    private ProductSizesEntity getProductSizeById(UUID sizeId) {
        return productSizesRepository.findById(sizeId)
                .orElseThrow(() -> new IllegalArgumentException("Product size not found with id: " + sizeId));
    }

    private ProductColorsEntity getProductColorById(UUID colorId) {
        return productColorsJpaRepository.findById(colorId)
                .orElseThrow(() -> new IllegalArgumentException("Product color not found with id: " + colorId));
    }
}
