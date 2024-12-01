package com.fns.reporting.service.domain.event;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.reporting.service.domain.entity.Report;
import java.time.ZonedDateTime;

public class ReportCreatedEvent extends ReportEvent {
//    private final DomainEventPublisher<ReportCreatedEvent> eventPublisher;

    public ReportCreatedEvent(Report report, ZonedDateTime createdAt
//                              DomainEventPublisher<ReportCreatedEvent> eventPublisher
    ) {
        super(report, createdAt);
//        this.eventPublisher = eventPublisher;
    }

    @Override
    public void fire() {

    }
}
