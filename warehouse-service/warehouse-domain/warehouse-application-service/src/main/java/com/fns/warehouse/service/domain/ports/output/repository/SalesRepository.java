package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.dto.create.SalesReportCommand;
import com.fns.warehouse.service.domain.dto.response.SalesIncomeResponse;
import com.fns.warehouse.service.domain.dto.response.SalesReportResponse;

import java.util.UUID;

public interface SalesRepository {
    SalesIncomeResponse getAllSalesIncome();

    SalesReportResponse getSalesReport(UUID productId);
}
