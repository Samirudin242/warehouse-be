package com.fns.warehouse.service.domain.ports.output.repository;


import com.fns.warehouse.service.domain.dto.get.GetAllWarehouseResponse;
import com.fns.warehouse.service.domain.entity.Warehouse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface WarehouseRepository {

    Warehouse saveWarehouse(Warehouse warehouse);

    Page<GetAllWarehouseResponse> getAllWarehouse(Integer page, Integer size, String name);

    List<Warehouse> getAllWarehouse(List<UUID> ids);

}
