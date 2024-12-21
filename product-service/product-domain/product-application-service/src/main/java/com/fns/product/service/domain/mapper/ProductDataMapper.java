package com.fns.product.service.domain.mapper;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.ProductResponse;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.valueObject.Gender;
import org.springframework.stereotype.Component;

@Component
public class ProductDataMapper {

    public ProductResponse createProductResponse(CreateProductCommand createProductCommand) {
        return ProductResponse.builder()
                .id(createProductCommand.getId())
                .sku(createProductCommand.getSku())
                .name(createProductCommand.getName())
                .sku(createProductCommand.getSku())
                .description(createProductCommand.getDescription())
                .slug(createProductCommand.getSlug())
                .build();
    }

    public Product createProduct(CreateProductCommand createProductCommand) {
        return Product.builder()
                .id(createProductCommand.getId())
                .sku(createProductCommand.getSku())
                .name(createProductCommand.getName())
                .gender(new Gender(createProductCommand.getGender()).toStringValue())
                .description(createProductCommand.getDescription())
                .brandId(createProductCommand.getBrand_id())
                .productCategoryId(createProductCommand.getProduct_categories_id())
                .sizeId(createProductCommand.getSize_id())
                .colorId(createProductCommand.getColor_id())
                .build();
    }

}
