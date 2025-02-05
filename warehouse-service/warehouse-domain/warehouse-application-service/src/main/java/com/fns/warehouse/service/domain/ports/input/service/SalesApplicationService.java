package com.fns.warehouse.service.domain.ports.input.service;

import com.fns.warehouse.service.domain.dto.response.SalesIncomeResponse;
import org.springframework.stereotype.Component;

@Component
public interface SalesApplicationService {

    SalesIncomeResponse getTotalIncome();

}
