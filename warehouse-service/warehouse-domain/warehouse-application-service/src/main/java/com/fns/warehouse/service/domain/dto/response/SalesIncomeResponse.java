package com.fns.warehouse.service.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SalesIncomeResponse {
    private Double totalIncome;
    private Double percentage;
}


