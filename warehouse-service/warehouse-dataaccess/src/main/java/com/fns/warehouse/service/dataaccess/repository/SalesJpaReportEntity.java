package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.SalesReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalesJpaReportEntity extends JpaRepository<SalesReportEntity, UUID> {
}
