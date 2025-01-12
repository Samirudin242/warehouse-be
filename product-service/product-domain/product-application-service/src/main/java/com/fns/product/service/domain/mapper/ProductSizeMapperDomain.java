package com.fns.product.service.domain.mapper;

import com.fns.product.service.domain.dto.get.ProductSizeBrandResponse;
import com.fns.product.service.domain.entity.ProductSizes;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeMapperDomain {

    public ProductSizeBrandResponse productSizeResponse(ProductSizes productSize) {
        return ProductSizeBrandResponse.builder()
                .id(productSize.getId())
                .size(productSize.getSize())
                .build();
    }
}
