package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.response.SalesIncomeResponse;
import com.fns.warehouse.service.domain.ports.input.service.SalesApplicationService;
import com.fns.warehouse.service.domain.ports.output.repository.SalesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SalesApplicationServiceImpl implements SalesApplicationService {

    private final SalesRepository salesRepository;

    public SalesApplicationServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public SalesIncomeResponse getTotalIncome() {
        return salesRepository.getAllSalesIncome();
    }
}
