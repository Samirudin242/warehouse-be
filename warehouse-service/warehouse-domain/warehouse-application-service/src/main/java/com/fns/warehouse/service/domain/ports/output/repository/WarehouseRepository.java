package com.fns.warehouse.service.domain.ports.output.repository;


import com.fns.warehouse.service.domain.entity.Warehouse;

public interface WarehouseRepository {

    Warehouse saveWarehouse(Warehouse warehouse);

}
