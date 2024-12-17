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
@Table(name = "product_colors")
@Entity
public class ProductColors {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String original_name;

    private String filter_group;
}
