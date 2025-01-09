package com.fns.warehouse.service.domain.ports.output.repository;


import com.fns.warehouse.service.domain.dto.get.GetAllWarehouseResponse;
import com.fns.warehouse.service.domain.entity.Warehouse;
import org.springframework.data.domain.Page;

public interface WarehouseRepository {

    Warehouse saveWarehouse(Warehouse warehouse);

    Page<GetAllWarehouseResponse> getAllWarehouse(Integer page, Integer size);

}
