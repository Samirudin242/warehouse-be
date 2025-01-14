package com.fns.warehouse.service.domain.ports.input.service;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.get.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.get.GetAllWarehouseResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public interface WarehouseApplicationService {
    CreateWarehouseResponse createWarehouse(@Valid CreateWarehouseCommand createWarehouseCommand);

    Page<GetAllWarehouseResponse> getAllWarehouse(Integer page, Integer pageSize, String name);
}
