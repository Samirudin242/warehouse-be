package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.dto.response.SalesIncomeResponse;

public interface SalesRepository {
    SalesIncomeResponse getAllSalesIncome();
}
