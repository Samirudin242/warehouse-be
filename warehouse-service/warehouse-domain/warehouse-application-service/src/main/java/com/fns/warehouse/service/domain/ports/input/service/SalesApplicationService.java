package com.fns.warehouse.service.domain.ports.input.service;

import com.fns.warehouse.service.domain.dto.create.SalesReportCommand;
import com.fns.warehouse.service.domain.dto.response.SalesIncomeResponse;
import com.fns.warehouse.service.domain.dto.response.SalesReportResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface SalesApplicationService {

    SalesIncomeResponse getTotalIncome();

    SalesReportResponse getSalesReport(UUID productId);

}
