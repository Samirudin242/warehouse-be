package com.fns.product.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warehouse")
@Entity
public class WarehouseEntity{
    @Id
    private UUID id;

    private String name;

    private WarehouseStatus status;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private UserEntity user;

    public enum WarehouseStatus {
        ACTIVE,
        INACTIVE,
    }
}

