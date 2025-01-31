package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockJpaRepository extends JpaRepository<StockEntity, UUID> {
    List<StockEntity> findAllByProduct_Id(UUID productId);

    Optional<StockEntity> findByProduct_IdAndWarehouse_Id(UUID productId, UUID warehouseId);

}
