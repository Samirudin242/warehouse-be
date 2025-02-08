package com.fns.warehouse.service.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SalesReportResponse {
    private List<Integer> sales;
    private String productName;
}
