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
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID id;

    private String name;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private UserEntity user;
}
