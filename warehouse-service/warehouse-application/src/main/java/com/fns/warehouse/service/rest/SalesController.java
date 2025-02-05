package com.fns.warehouse.service.rest;

import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.response.SalesIncomeResponse;
import com.fns.warehouse.service.domain.ports.input.service.SalesApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/sales", produces = "application/vnd.api.v1+json")
public class SalesController {
    private final SalesApplicationService salesApplicationService;

    public SalesController(SalesApplicationService salesApplicationService) {
        this.salesApplicationService = salesApplicationService;
    }

    @GetMapping("/all-income")
    public ResponseEntity<SalesIncomeResponse> getAllOrder()  {
        SalesIncomeResponse salesIncomeResponse = salesApplicationService.getTotalIncome();

        return ResponseEntity.ok(salesIncomeResponse);
    }
}
