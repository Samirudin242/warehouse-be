package com.fns.warehouse.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_colors")
@Entity
public class ProductColorEntity extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String originalName;

    private String filterGroup;

}
