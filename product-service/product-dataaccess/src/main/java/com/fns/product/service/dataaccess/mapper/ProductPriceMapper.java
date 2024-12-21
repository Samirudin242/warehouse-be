package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.ProductPricesEntity;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.domain.entity.ProductPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductPriceMapper {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    public ProductPricesEntity priceToPriceEntity(ProductPrices productPrice) {

        ProductEntity productEntity = getProductBrandById(productPrice.getProductId());

        // Map the ProductPrices to ProductPricesEntity
        return ProductPricesEntity.builder()
                .id(productPrice.getId())
                .currency(productPrice.getCurrency())
                .price(productPrice.getPrice())
                .discounted_value(productPrice.getDiscountedValue())
                .on_sales(productPrice.getOnSales())
                .product(productEntity)
                .build();
    }


    public ProductPrices priceFromPriceEntity(ProductPricesEntity productPriceEntity) {

        return ProductPrices.builder()
                .id(productPriceEntity.getId())
                .currency(productPriceEntity.getCurrency())
                .price(productPriceEntity.getPrice())
                .discountedValue(productPriceEntity.getDiscounted_value())
                .onSales(productPriceEntity.getOn_sales())
                .productId(productPriceEntity.getProduct().getId())
                .build();
    }

    private ProductEntity getProductBrandById(UUID id) {
        return productJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product brand not found with id: " + id));
    }
}
