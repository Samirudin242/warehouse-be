package com.fns.product.service.dataaccess.entity;

import lombok.extern.slf4j.Slf4j;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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
    private ProductSizesEntity productSize;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private ProductColorsEntity productColor;

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
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column()
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    public void beforeSave() {
        log.info("Before saving: {}", this);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", slug='" + slug + '\'' +
                ", gender='" + gender + '\'' +
                ", brand=" + (brand != null ? brand.getId() : "null") +
                ", category=" + (category != null ? category.getId() : "null") +
                ", productSize=" + (productSize != null ? productSize.getId() : "null") +
                ", productColor=" + (productColor != null ? productColor.getId() : "null") +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
