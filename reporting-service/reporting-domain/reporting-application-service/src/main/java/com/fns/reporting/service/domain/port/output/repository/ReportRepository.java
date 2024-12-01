package com.fns.reporting.service.domain.port.output.repository;

import com.fns.domain.valueobject.ReportId;
import com.fns.reporting.service.domain.entity.Report;

import java.util.Optional;

public interface ReportRepository {
    Report save(Report report);

    Optional<Report> findById(ReportId reportId);

}
