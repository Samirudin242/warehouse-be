package com.fns.reporting.service.domain;


import com.fns.domain.event.EmptyEvent;
import com.fns.domain.valueobject.ReportId;
import com.fns.reporting.service.domain.dto.message.WarehouseRequest;
import com.fns.reporting.service.domain.entity.Report;
import com.fns.reporting.service.domain.event.ReportCreatedEvent;
import com.fns.reporting.service.domain.exception.ReportNotFoundException;
import com.fns.reporting.service.domain.port.output.repository.ReportRepository;
import com.fns.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class ReportWarehouseSaga implements SagaStep<WarehouseRequest, ReportCreatedEvent, EmptyEvent> {
    private final ReportingDomainService reportingDomainService;
    private final ReportRepository reportRepository;

    public ReportWarehouseSaga(ReportingDomainService reportingDomainService,
                               ReportRepository reportRepository){
        this.reportingDomainService = reportingDomainService;
        this.reportRepository = reportRepository;
    }


    @Override
    @Transactional
    public ReportCreatedEvent process(WarehouseRequest warehouseRequest) {
        log.info("Completing report for warehouse with id: {}", warehouseRequest.getWarehouseId());
        Report report = findOrder(warehouseRequest.getWarehouseId());
        ReportCreatedEvent domainEvent = reportingDomainService.validateAndInitiateReport(report.getTitle(),report.getContent());
        reportRepository.save(report);
        log.info("Report with id: {} is paid", report.getId().getValue());
        return domainEvent;
    }

    @Override
    @Transactional
    public EmptyEvent rollback(WarehouseRequest warehouseRequest) {
        log.info("Cancelling report with id: {}", warehouseRequest.getWarehouseId());
        Report report = findOrder(warehouseRequest.getWarehouseId());
//        reportingDomainService.cancelOrder(report, warehouseRequest.getFailureMessage());
//        reportRepository.save(report);
        log.info("Report with id: {} is cancelled", report.getId().getValue());
        return EmptyEvent.INSTANCE;
    }

    private Report findOrder(String reportId) {
        Optional<Report> reportResponse = reportRepository.findById(new ReportId(UUID.fromString(reportId)));
        if (reportResponse.isEmpty()) {
            log.error("Report with id: {} could not be found!", reportId);
            throw new ReportNotFoundException("Report with id " + reportId + " could not be found!");
        }
        return reportResponse.get();
    }

}
