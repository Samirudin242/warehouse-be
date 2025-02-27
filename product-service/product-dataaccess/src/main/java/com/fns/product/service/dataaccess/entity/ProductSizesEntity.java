package com.fns.product.service.dataaccess.entity;

import com.fns.product.service.domain.entity.ProductSizes;
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
public class ProductSizesEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String size;

    private Boolean isStock;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAndSizeEntity> productAndSizes;

    public Object toDomain() {
        return new ProductSizes(this.id, this.size, this.isStock);
    }
}
