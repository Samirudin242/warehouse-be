package com.fns.warehouse.service.messaging.mapper;

import com.fns.warehouse.service.domain.dto.message.ProductModel;
import com.fns.warehouse.service.messaging.model.ProductKafkaModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMessagingMapper {

    public ProductModel mapToProductModel(ProductKafkaModel productKafkaModel) {
        return ProductModel.builder()
                .id(productKafkaModel.getId())
                .sku(productKafkaModel.getSku())
                .name(productKafkaModel.getName())
                .slug(productKafkaModel.getSlug())
                .description(productKafkaModel.getDescription())
                .price(productKafkaModel.getPrice())
                .gender(productKafkaModel.getGender())
                .imageUrl(productKafkaModel.getImageUrl())
                .warehouseId(productKafkaModel.getWarehouseId())
                .stock(productKafkaModel.getStock())
                .build();
    }

}
