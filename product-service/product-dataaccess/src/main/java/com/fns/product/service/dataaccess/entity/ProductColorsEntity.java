package com.fns.product.service.dataaccess.entity;

import com.fns.product.service.domain.entity.ProductColors;
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
public class ProductColorsEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String originalName;

    private String filterGroup;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ProductColors toDomain() {
        return new ProductColors(this.id, this.originalName, this.filterGroup);
    }
}
