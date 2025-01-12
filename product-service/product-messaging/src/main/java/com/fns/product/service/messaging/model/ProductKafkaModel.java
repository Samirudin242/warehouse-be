package com.fns.product.service.messaging.model;

import com.fns.product.service.domain.valueObject.Price;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Setter
@Getter
@Builder
public class ProductKafkaModel {
    private UUID id;
    private String sku;
    private String name;
    private String slug;
    private String description;
    private String gender;
    private Price price;
    private UUID brandId;
    private UUID productCategoryId;
    private UUID sizeId;
    private UUID colorId;
    private String imageUrl;
    private UUID warehouseId;
    private Integer stock;
}
