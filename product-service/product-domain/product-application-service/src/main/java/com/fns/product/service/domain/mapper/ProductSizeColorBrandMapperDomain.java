package com.fns.product.service.domain.mapper;

import com.fns.product.service.domain.dto.get.ProductSizeBrandColorResponse;
import com.fns.product.service.domain.entity.ProductBrand;
import com.fns.product.service.domain.entity.ProductColors;
import com.fns.product.service.domain.entity.ProductSizes;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeColorBrandMapperDomain {

    public ProductSizeBrandColorResponse productSizeResponse(ProductSizes productSize) {
        return ProductSizeBrandColorResponse.builder()
                .id(productSize.getId())
                .size(productSize.getSize())
                .build();
    }

    public ProductSizeBrandColorResponse productBrandResponse(ProductBrand productBrand) {
        return ProductSizeBrandColorResponse.builder()
                .id(productBrand.getId())
                .brand(productBrand.getName())
                .build();
    }

    public ProductSizeBrandColorResponse productColorResponse(ProductColors productColors) {
        return ProductSizeBrandColorResponse.builder()
                .id(productColors.getId())
                .brand(productColors.getOriginalName())
                .build();
    }
}
