package com.fns.product.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_reviews")
@Entity
public class ProductReviews {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private Integer rating;

    private Integer count;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;
}
