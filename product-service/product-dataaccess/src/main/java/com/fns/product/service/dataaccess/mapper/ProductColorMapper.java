package com.fns.product.service.dataaccess.mapper;


import com.fns.product.service.dataaccess.entity.ProductColorsEntity;
import com.fns.product.service.domain.entity.ProductColors;
import org.springframework.stereotype.Component;

@Component
public class ProductColorMapper {

    public ProductColorsEntity colorToColorEntity(ProductColors productColor) {
        // Map the ProductColor to ProductColorEntity
        return ProductColorsEntity.builder()
                .id(productColor.getId())
                .originalName(productColor.getOriginalName())
                .filterGroup(productColor.getFilterGroup())
                .build();
    }

    public ProductColors colorFromColorEntity(ProductColorsEntity productColorEntity) {
        // Map the ProductColorEntity to ProductColor
        return ProductColors.builder()
                .id(productColorEntity.getId())
                .originalName(productColorEntity.getOriginalName())
                .filterGroup(productColorEntity.getFilterGroup())
                .build();
    }
}
