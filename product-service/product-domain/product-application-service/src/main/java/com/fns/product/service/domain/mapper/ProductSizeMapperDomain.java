package com.fns.product.service.domain.mapper;

import com.fns.product.service.domain.dto.get.ProductSizeResponse;
import com.fns.product.service.domain.entity.ProductSizes;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeMapper {

    public ProductSizeResponse productSizeResponse(ProductSizes productSize) {
        return ProductSizeResponse.builder()
                .id(productSize.getId())
                .size(productSize.getSize())
                .build();
    }
}
