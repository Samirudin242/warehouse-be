package com.fns.reporting.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.reporting.service.domain.event.ReportCreatedEvent;
import com.fns.reporting.service.domain.exception.ReportingDomainException;

public interface ReportingDomainService {

    ReportCreatedEvent validateAndInitiateReport(String title, String content) throws ReportingDomainException;


}
