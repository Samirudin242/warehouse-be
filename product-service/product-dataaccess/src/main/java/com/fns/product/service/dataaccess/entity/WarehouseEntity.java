package com.fns.product.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "warehouse")
@Entity
public class WarehouseEntity{
    @Id
    private UUID id;

    private String name;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private UserEntity user;
}

