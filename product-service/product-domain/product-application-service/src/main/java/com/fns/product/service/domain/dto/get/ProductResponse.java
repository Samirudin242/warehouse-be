package com.fns.product.service.domain.dto.get;

import com.fns.product.service.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
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
    private final ProductBrand brand;
    private final ProductCategories productCategory;
    private final String warehouse;
    private final String imageUrl;
    private final Integer stock;
    private final Double price;
    private final Integer totalSell;
    private final Integer rating;
    private final List<ProductAndSize> productAndSizes;
    private final List<ProductAndColor> productAndColors;
    private final List<ProductImages> listImages;
    private final ProductPrices productPrice;
}
