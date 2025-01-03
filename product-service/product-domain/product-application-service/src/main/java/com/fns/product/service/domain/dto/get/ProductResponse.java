package com.fns.product.service.domain.dto.create;

import com.fns.product.service.domain.entity.ProductBrand;
import com.fns.product.service.domain.entity.ProductCategories;
import com.fns.product.service.domain.entity.ProductColors;
import com.fns.product.service.domain.entity.ProductSizes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ProductResponse {

    private final UUID id;
    private final String name;
    private final String sku;
    private final String description;
    private final String slug;
    private final String gender;
    private ProductSizes sizes;
    private ProductBrand brand;  // Add brand information
    private ProductCategories productCategory;  // Add product category information
    private ProductColors color;  // Add color information
}
