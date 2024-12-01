package com.fns.reporting.service.domain;

import com.fns.reporting.service.domain.dto.message.WarehouseRequest;
//import com.fns.reporting.service.domain.event.OrderPaidEvent;
import com.fns.reporting.service.domain.event.ReportCreatedEvent;
import com.fns.reporting.service.domain.port.input.message.listener.warehouse.WarehouseRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.fns.reporting.service.domain.entity.Report.FAILURE_MESSAGE_DELIMITER;

@Validated
@Service
@Slf4j
public class WarehouseRequestMessageListenerImpl implements WarehouseRequestMessageListener {
    private final ReportWarehouseSaga reportWarehouseSaga;

    public WarehouseRequestMessageListenerImpl(ReportWarehouseSaga reportWarehouseSaga) {
        this.reportWarehouseSaga = reportWarehouseSaga;
    }

    @Override
    public void warehouseRequestCompleted(WarehouseRequest warehouseRequest) {
        ReportCreatedEvent domainEvent = reportWarehouseSaga.process(warehouseRequest);
        log.info("Publishing ReportCreatedEvent for warehouse id: {}", warehouseRequest.getWarehouseId());
        domainEvent.fire();
    }

    @Override
    public void warehouseRequestCanceled(WarehouseRequest warehouseRequest) {
        reportWarehouseSaga.rollback(warehouseRequest);
        log.info("Report is roll backed for report id: {} with failure messages: {}",
                warehouseRequest.getWarehouseId(),
                String.join(FAILURE_MESSAGE_DELIMITER, warehouseRequest.getFailureMessage()));
    }
}

