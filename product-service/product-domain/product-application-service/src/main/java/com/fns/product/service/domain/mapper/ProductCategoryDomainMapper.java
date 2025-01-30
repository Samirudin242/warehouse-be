package com.fns.product.service.domain.mapper;

import com.fns.product.service.domain.dto.get.ProductCategoryResponse;
import com.fns.product.service.domain.entity.ProductCategories;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryDomainMapper {
    public ProductCategoryResponse productCategoryResponse(ProductCategories productCategory) {
        // Map ProductCategory to ProductCategoryResponse
        return ProductCategoryResponse.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .slug(productCategory.getSlug())
                .parentId(productCategory.getParentId())
                .build();
    }
}
