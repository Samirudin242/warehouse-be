package com.fns.warehouse.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_sizes")
@Entity
public class ProductSizeEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String size;

    private Boolean isStock;

}
