package com.fns.product.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_categories")
@Entity
public class ProductCategoriesEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private String slug;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products;
}
