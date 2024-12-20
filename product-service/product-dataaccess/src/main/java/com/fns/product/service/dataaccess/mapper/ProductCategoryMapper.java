package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductCategoriesEntity;
import com.fns.product.service.domain.entity.ProductCategories;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {

    public ProductCategoriesEntity categoryToCategoryEntity(ProductCategories productCategory) {
        // Map the ProductCategory to ProductCategoryEntity
        return ProductCategoriesEntity.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .slug(productCategory.getSlug())
                .parentId(productCategory.getParentId())
                .build();
    }

    public ProductCategories categoryFromCategoryEntity(ProductCategoriesEntity productCategoryEntity) {
        // Map the ProductCategoryEntity to ProductCategory
        return ProductCategories.builder()
                .id(productCategoryEntity.getId())
                .name(productCategoryEntity.getName())
                .slug(productCategoryEntity.getSlug())
                .parentId(productCategoryEntity.getParentId())
                .build();
    }
}
