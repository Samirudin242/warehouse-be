package com.fns.product.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
@Entity
public class BrandEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private String slug;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products;
}
