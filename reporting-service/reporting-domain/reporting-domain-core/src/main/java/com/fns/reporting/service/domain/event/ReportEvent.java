package com.fns.reporting.service.domain.event;

import com.fns.domain.event.DomainEvent;
import com.fns.reporting.service.domain.entity.Report;
import java.time.ZonedDateTime;

public abstract class ReportEvent implements DomainEvent<Report> {
    private final Report report;
    private final ZonedDateTime createdAt;

    protected ReportEvent(Report report, ZonedDateTime createdAt) {
        this.report = report;
        this.createdAt = createdAt;
    }

    @Override
    public Report getEntity() {
        return report;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
