package com.fns.product.service.dataaccess.mapper;


import com.fns.product.service.dataaccess.entity.ProductBrandEntity;
import com.fns.product.service.dataaccess.entity.ProductColorsEntity;
import com.fns.product.service.dataaccess.entity.ProductSizesEntity;
import com.fns.product.service.domain.entity.ProductBrand;
import com.fns.product.service.domain.entity.ProductColors;
import com.fns.product.service.domain.entity.ProductSizes;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeColorBrandMapper {

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

    public ProductBrand brandFromBrandEntity(ProductBrandEntity productBrandEntity) {
        // Map the ProductBrandEntity to ProductBrand
        return ProductBrand.builder()
                .id(productBrandEntity.getId())
                .name(productBrandEntity.getName())
                .slug(productBrandEntity.getSlug())
                .build();

    }

    public ProductColors productFromColorEntity(ProductColorsEntity productColorsEntity) {
        // Map the ProductColorsEntity to ProductColors
        return ProductColors.builder()
                .id(productColorsEntity.getId())
                .originalName(productColorsEntity.getOriginalName())
                .filterGroup(productColorsEntity.getFilterGroup())
                .build();
    }

}
