package com.fns.product.service.domain.dto.create;

//import com.fns.product.service.dataaccess.entity.ProductBrandEntity;
//import com.fns.product.service.dataaccess.entity.ProductCategoriesEntity;
//import com.fns.product.service.dataaccess.entity.ProductColors;
//import com.fns.product.service.dataaccess.entity.ProductSizes;
import com.fns.product.service.domain.entity.ProductSizes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CreateProductResponse {

    private final UUID id;

    private final String name;

    private final String sku;

    private final String description;

    private final String slug;

    private final String gender;

//    private ProductBrandEntity brand;
//
//    private ProductCategoriesEntity category;
//
    private ProductSizes sizes;
//
//    private Prod colors;
}
