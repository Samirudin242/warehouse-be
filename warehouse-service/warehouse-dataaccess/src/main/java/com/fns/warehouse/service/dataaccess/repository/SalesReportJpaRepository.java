package com.fns.warehouse.service.dataaccess.repository;

import com.fns.warehouse.service.dataaccess.entity.SalesReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface SalesReportJpaRepository extends JpaRepository<SalesReportEntity, UUID> {
    List<SalesReportEntity> findByTransactionDateAfter(Date startDate);
    List<SalesReportEntity> findByTransactionDateBetween(Date startDate, Date endDate);

    @Query("""
        SELECT MONTH(s.transactionDate), SUM(s.totalPrice) 
        FROM SalesReportEntity s 
        WHERE (:productId IS NULL OR s.product.id = :productId)
        GROUP BY MONTH(s.transactionDate)
        ORDER BY MONTH(s.transactionDate)
    """)
    List<Object[]> getSalesByMonth(@Param("productId") UUID productId);
}
