package com.fns.product.service.domain.mapper;


import com.fns.product.service.domain.dto.message.WarehouseModel;
import com.fns.product.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataMapper {
    public Warehouse warehouseModelToWarehouse(WarehouseModel warehouseModel) {
        // Map the WarehouseModel to Warehouse entity
        return Warehouse.builder()
                .id(warehouseModel.getId())
                .admin_id(warehouseModel.getAdminId())
                .location_id(warehouseModel.getLocationId())
                .name(warehouseModel.getName())
                .address(warehouseModel.getAddress())
                .city(warehouseModel.getCity())
                .city_id(warehouseModel.getCityId())
                .province(warehouseModel.getProvince())
                .province_id(warehouseModel.getProvinceId())
                .postal_code(warehouseModel.getPostalCode())
                .build();
    }
}
