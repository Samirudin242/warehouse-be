package com.fns.warehouse.service.messaging.mapper;

import com.fns.warehouse.service.domain.dto.message.UserModel;
import com.fns.warehouse.service.domain.entity.Location;
import com.fns.warehouse.service.domain.entity.Stock;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.messaging.model.StockKafkaModel;
import com.fns.warehouse.service.messaging.model.UserKafkaModel;
import com.fns.warehouse.service.messaging.model.WarehouseKafkaModel;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMessagingMapper {
    public UserModel mapToUserModel(UserKafkaModel userKafkaModel) {

        return UserModel.builder()
                .id(userKafkaModel.getId())
                .name(userKafkaModel.getName())
                .userName(userKafkaModel.getUserName())
                .email(userKafkaModel.getEmail())
                .phoneNumber(userKafkaModel.getPhoneNumber())
                .profilePicture(userKafkaModel.getProfilePicture())
                .address(userKafkaModel.getAddress())
                .cityId(userKafkaModel.getCityId())
                .provinceId(userKafkaModel.getProvinceId())
                .roleId(userKafkaModel.getRoleId())
                .build();
    }

    public WarehouseKafkaModel convertToWarehouse(Warehouse warehouse) {
        return new WarehouseKafkaModel(
                warehouse.getId(),
                warehouse.getAdmin_id(),
                warehouse.getLocation_id(),
                warehouse.getName(),
                warehouse.getAddress(),
                warehouse.getCity(),
                warehouse.getCity_id(),
                warehouse.getProvince(),
                warehouse.getProvince_id(),
                warehouse.getPostal_code(),
                warehouse.getLatitude(),
                warehouse.getLongitude()
        );
    }

    public StockKafkaModel convertToStockKafkaModel(Stock stock) {
        return StockKafkaModel.builder()
                .id(stock.getId())
                .product_id(stock.getProduct_id())
                .warehouse_id(stock.getWarehouse_id())
                .quantity(stock.getQuantity())
                .build();
    }
}
