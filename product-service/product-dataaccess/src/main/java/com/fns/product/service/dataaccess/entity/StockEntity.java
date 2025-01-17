package com.fns.product.service.dataaccess.entity;

import com.fns.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock")
@Entity
public class StockEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private UUID warehouseId;

    private UUID productId;

    private Integer quantity;
}

