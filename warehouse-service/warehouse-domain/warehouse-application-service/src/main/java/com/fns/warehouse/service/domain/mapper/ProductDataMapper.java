package com.fns.warehouse.service.domain.mapper;

import com.fns.warehouse.service.domain.dto.message.ProductModel;
import com.fns.warehouse.service.domain.entity.Product;
import com.fns.warehouse.service.domain.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class ProductDataMapper {

    public Product productModelToProduct(ProductModel productModel) {
        return Product.builder()
                .id(productModel.getId())
                .sku(productModel.getSku())
                .name(productModel.getName())
                .slug(productModel.getSlug())
                .description(productModel.getDescription())
                .gender(productModel.getGender())
                .price(productModel.getPrice())
                .brandId(productModel.getBrandId())
                .productCategoryId(productModel.getProductCategoryId())
                .sizeId(productModel.getSizeId())
                .colorId(productModel.getColorId())
                .imageUrl(productModel.getImageUrl())
                .warehouseId(productModel.getWarehouseId())
                .stock(productModel.getStock())
                .build();
    }

    public Stock productModelToStock(ProductModel productModel) {
        return Stock.builder()
                .quantity(productModel.getStock())
                .product_id(productModel.getId())
                .warehouse_id(productModel.getWarehouseId())
                .build();
    }

}
