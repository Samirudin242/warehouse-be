package com.fns.product.service.dataaccess.entity;

import com.fns.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
@Entity
public class LocationEntity extends BaseEntity {
    @Id
    private UUID id;

    private String address;

    private String province;

    private String city;

    private String province_id;

    private String city_id;

    private String postal_code;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity users;

    @OneToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private WarehouseEntity warehouses;
}
