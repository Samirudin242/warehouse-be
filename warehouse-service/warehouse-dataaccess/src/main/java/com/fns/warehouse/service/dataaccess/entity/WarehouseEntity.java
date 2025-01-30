package com.fns.warehouse.service.dataaccess.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouse")
@Entity
public class WarehouseEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
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
