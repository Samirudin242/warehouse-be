package com.fns.product.service.dataaccess.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String sku;

    private String name;

    private String description;

    private String slug;

    private String gender;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private ProductBrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "product_categories_id", referencedColumnName = "id")
    private ProductCategoriesEntity category;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private ProductSizes productSize;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private ProductColors productColor;

    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<ProductImages> images;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductPricesEntity productPrices;

    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<ProductReviews> reviews;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
