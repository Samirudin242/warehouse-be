package com.fns.product.service.dataaccess.mapper;


import com.fns.product.service.dataaccess.entity.ProductSizesEntity;
import com.fns.product.service.domain.entity.ProductSizes;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeMapper {

    public ProductSizesEntity sizeToSizeEntity(ProductSizes productSize) {
        // Map the ProductSize to ProductSizeEntity
        return ProductSizesEntity.builder()
                .id(productSize.getId())
                .size(productSize.getSize())
                .isStock(productSize.getIsStock())
                .build();
    }

    public ProductSizes sizeFromSizeEntity(ProductSizesEntity productSizeEntity) {
        // Map the ProductSizeEntity to ProductSize
        return ProductSizes.builder()
                .id(productSizeEntity.getId())
                .size(productSizeEntity.getSize())
                .isStock(productSizeEntity.getIsStock())
                .build();
    }
}
