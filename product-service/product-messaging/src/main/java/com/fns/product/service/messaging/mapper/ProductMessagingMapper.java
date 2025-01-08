package com.fns.product.service.messaging.mapper;

import com.fns.product.service.domain.dto.message.WarehouseModel;
import com.fns.product.service.messaging.model.WarehouseKafkaModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMessagingMapper {

    public WarehouseModel mapToWarehouseModel(WarehouseKafkaModel warehouseKafkaModel) {
        return WarehouseModel.builder()
                .id(warehouseKafkaModel.getId())
                .adminId(warehouseKafkaModel.getAdmin_id())
                .locationId(warehouseKafkaModel.getLocation_id())
                .name(warehouseKafkaModel.getName())
                .address(warehouseKafkaModel.getAddress())
                .city(warehouseKafkaModel.getCity())
                .cityId(warehouseKafkaModel.getCity_id())
                .province(warehouseKafkaModel.getProvince())
                .provinceId(warehouseKafkaModel.getProvince_id())
                .postalCode(warehouseKafkaModel.getPostal_code())
                .build();
    }

}
