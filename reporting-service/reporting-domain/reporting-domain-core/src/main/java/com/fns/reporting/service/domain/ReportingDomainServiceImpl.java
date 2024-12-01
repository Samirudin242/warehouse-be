package com.fns.reporting.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.reporting.service.domain.entity.*;
import com.fns.reporting.service.domain.event.*;
import com.fns.reporting.service.domain.exception.ReportingDomainException;
import com.fns.domain.valueobject.*;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ReportingDomainServiceImpl implements ReportingDomainService {

    @Override
    public ReportCreatedEvent validateAndInitiateReport(String title, String content) throws ReportingDomainException {
        Report report = Report.builder()
                .reportId(new ReportId(UUID.randomUUID()))
                .title(title)
                .content(content)
                .build();

        return new ReportCreatedEvent(report, ZonedDateTime.now());
    }

}
