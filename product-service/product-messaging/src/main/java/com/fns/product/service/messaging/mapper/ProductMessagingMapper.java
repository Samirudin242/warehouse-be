package com.fns.product.service.messaging.mapper;

import com.fns.product.service.domain.dto.message.WarehouseModel;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.messaging.model.ProductKafkaModel;
import com.fns.product.service.messaging.model.WarehouseKafkaModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMessagingMapper {

    public WarehouseModel mapToWarehouseModel(WarehouseKafkaModel warehouseKafkaModel) {
        return WarehouseModel.builder()
                .id(warehouseKafkaModel.getId())
                .adminId(warehouseKafkaModel.getAdminId())
                .locationId(warehouseKafkaModel.getLocationId())
                .name(warehouseKafkaModel.getName())
                .address(warehouseKafkaModel.getAddress())
                .city(warehouseKafkaModel.getCity())
                .cityId(warehouseKafkaModel.getCityId())
                .province(warehouseKafkaModel.getProvince())
                .provinceId(warehouseKafkaModel.getProvinceId())
                .postalCode(warehouseKafkaModel.getPostalCode())
                .latitude(warehouseKafkaModel.getLatitude())
                .longitude(warehouseKafkaModel.getLongitude())
                .build();
    }

    public ProductKafkaModel covertToProductKafkaModel(Product product) {
        return ProductKafkaModel.builder()
               .id(product.getId())
               .sku(product.getSku())
               .name(product.getName())
               .slug(product.getSlug())
               .description(product.getDescription())
               .gender(product.getGender())
               .price(product.getPrice())
               .brandId(product.getBrandId())
               .productCategoryId(product.getProductCategoryId())
               .imageUrl(product.getImageUrl())
               .warehouseId(product.getWarehouseId())
                .stock(product.getStock())
               .build();
    }

}
