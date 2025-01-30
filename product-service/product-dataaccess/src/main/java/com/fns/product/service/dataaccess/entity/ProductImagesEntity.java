package com.fns.product.service.dataaccess.entity;

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
@Table(name = "product_images", indexes = @Index(columnList = "product_id"))
@Entity
public class ProductImagesEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String image_url;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "ProductImagesEntity{" +
                "id=" + id +
                ", image_url='" + image_url + '\'' +
                ", product_id=" + (product != null ? product.getId() : "null") +
                '}';
    }
}
