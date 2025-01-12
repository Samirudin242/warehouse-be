package com.fns.warehouse.service.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Data
public class Product {
    private UUID id;
    private String sku;
    private String name;
    private String slug;
    private String description;
    private String gender;
    private Double price;
    private UUID brandId;
    private UUID productCategoryId;
    private UUID sizeId;
    private UUID colorId;
    private String imageUrl;
    private UUID warehouseId;
    private Integer stock;
}
