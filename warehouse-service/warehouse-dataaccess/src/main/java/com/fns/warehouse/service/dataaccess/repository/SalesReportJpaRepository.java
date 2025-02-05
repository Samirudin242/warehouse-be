package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.SalesReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface SalesReportJpaRepository extends JpaRepository<SalesReportEntity, UUID> {
    List<SalesReportEntity> findByTransactionDateAfter(Date startDate);
    List<SalesReportEntity> findByTransactionDateBetween(Date startDate, Date endDate);
}
