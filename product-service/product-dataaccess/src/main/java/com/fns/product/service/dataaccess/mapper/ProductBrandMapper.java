package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductBrandEntity;
import com.fns.product.service.domain.entity.ProductBrand;
import org.springframework.stereotype.Component;

@Component
public class ProductBrandMapper {

    public ProductBrandEntity brandToBrandEntity(ProductBrand productBrand) {
        // Map the ProductBrand to ProductBrandEntity
        return ProductBrandEntity.builder()
                .id(productBrand.getId())
                .name(productBrand.getName())
                .build();
    }

    public ProductBrand brandFromBrandEntity(ProductBrandEntity productBrandEntity) {
        // Map the ProductBrandEntity to ProductBrand
        return ProductBrand.builder()
                .id(productBrandEntity.getId())
                .name(productBrandEntity.getName())
                .slug(productBrandEntity.getSlug())
                .build();
    }
}
