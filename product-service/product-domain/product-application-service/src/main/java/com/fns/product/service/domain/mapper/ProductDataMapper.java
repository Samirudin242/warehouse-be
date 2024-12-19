package com.fns.product.service.domain.mapper;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.CreateProductResponse;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.valueObject.Gender;
import com.fns.product.service.domain.valueObject.Price;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductDataMapper {

    public CreateProductResponse createProductResponse(CreateProductCommand createProductCommand) {
        return CreateProductResponse.builder()
                .sku(createProductCommand.getSku())
                .name(createProductCommand.getName())
                .sku(createProductCommand.getSku())
                .description(createProductCommand.getDescription())
                .slug(createProductCommand.getSlug())
                .build();
    }

    public Product createProduct(CreateProductCommand createProductCommand) {
        return Product.builder()
                .sku(createProductCommand.getSku())
                .name(createProductCommand.getName())
                .gender(new Gender(createProductCommand.getGender()))
                .description(createProductCommand.getDescription())
                .brandId(createProductCommand.getBrand_id())
                .productCategoryId(createProductCommand.getProduct_categories_id())
                .sizeId(createProductCommand.getSize_id())
                .build();
    }

}
