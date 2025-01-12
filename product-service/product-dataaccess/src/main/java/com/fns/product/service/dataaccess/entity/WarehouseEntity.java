package com.fns.product.service.dataaccess.entity;

import com.fns.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "warehouse")
@Entity
public class WarehouseEntity extends BaseEntity {
    @Id
    private UUID id;

    private String name;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private UserEntity user;
}

