package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.entity.ProductEntity;
import com.fns.warehouse.service.dataaccess.entity.SalesReportEntity;
import com.fns.warehouse.service.dataaccess.repository.ProductJpaRepository;
import com.fns.warehouse.service.dataaccess.repository.SalesReportJpaRepository;
import com.fns.warehouse.service.domain.dto.create.SalesReportCommand;
import com.fns.warehouse.service.domain.dto.response.SalesIncomeResponse;
import com.fns.warehouse.service.domain.dto.response.SalesReportResponse;
import com.fns.warehouse.service.domain.entity.Product;
import com.fns.warehouse.service.domain.ports.output.repository.SalesRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class SalesRepositoryImpl implements SalesRepository {
    private final SalesReportJpaRepository salesReportJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    public SalesRepositoryImpl(SalesReportJpaRepository salesReportJpaRepository, ProductJpaRepository productJpaRepository) {
        this.salesReportJpaRepository = salesReportJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }


    @Override
    public SalesIncomeResponse getAllSalesIncome() {
        LocalDate now = LocalDate.now();
        LocalDate startOfCurrentMonth = now.withDayOfMonth(1);
        LocalDate startOfLastMonth = startOfCurrentMonth.minusMonths(1);

        Date startDateCurrent = Date.from(startOfCurrentMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date startDateLast = Date.from(startOfLastMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //sales reports for current month
        double totalIncomeCurrentMonth = salesReportJpaRepository
                .findByTransactionDateAfter(startDateCurrent)
                .stream()
                .mapToDouble(SalesReportEntity::getTotalPrice)
                .sum();

        //  sales reports for last month
        double totalIncomeLastMonth = salesReportJpaRepository
                .findByTransactionDateBetween(startDateLast, startDateCurrent)
                .stream()
                .mapToDouble(SalesReportEntity::getTotalPrice)
                .sum();

        // Calculate percentage change
        double percentageChange = totalIncomeLastMonth == 0 ? 100.0 :
                ((totalIncomeCurrentMonth - totalIncomeLastMonth) / totalIncomeLastMonth) * 100;

        return SalesIncomeResponse.builder()
                .totalIncome(totalIncomeCurrentMonth)
                .percentage(percentageChange)
                .build();
    }

    @Override
    public SalesReportResponse getSalesReport(UUID productId) {
        ProductEntity productEntity = null;
        if (productId != null) {
            productEntity = productJpaRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        }

        List<Object[]> results = salesReportJpaRepository.getSalesByMonth(productId);

        List<Integer> monthlySales = new ArrayList<>(Collections.nCopies(12, 0));

        for (Object[] row : results) {
            int monthIndex = ((Number) row[0]).intValue() - 1;
            int totalSales = ((Number) row[1]).intValue();
            monthlySales.set(monthIndex, totalSales);
        }

        return SalesReportResponse.builder()
                .sales(monthlySales)
                .productName(productId == null ? "All Products" : productEntity.getName())
                .build();
    }

}
