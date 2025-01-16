package com.fns.product.service.domain.entity;

import com.fns.product.service.domain.valueObject.Price;
import lombok.*;

import java.lang.annotation.Documented;
import java.util.UUID;

// Aggregate Root: Product
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product {

    private UUID id;
    private String sku;
    private String name;
    private String slug;
    private String description;
    private String gender;
    private Double price;
    private UUID brandId;
    private UUID productCategoryId;
    private String imageUrl;
    private UUID warehouseId;
    private Integer stock;


    // Private constructor for builder
    private Product(Builder builder) {
        this.id = builder.id;
        this.sku = builder.sku;
        this.name = builder.name;
        this.slug = generateSlug(builder.name);
        this.gender = builder.gender;
        this.price = builder.price;
        this.description = builder.description;
        this.brandId = builder.brandId;
        this.productCategoryId = builder.productCategoryId;
        this.imageUrl = builder.imageUrl;
        this.warehouseId = builder.warehouseId;
        this.stock = builder.stock;
    }


    public static Builder builder() {
        return new Builder();
    }


    private String generateSlug(String name) {
        return name.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", gender=" + gender +
                ", price=" + price +
                ", brandId=" + brandId +
                ", productCategoryId=" + productCategoryId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }


    // Builder class
    public static class Builder {
        private UUID id;
        private String sku;
        private String name;
        private String slug;
        private String description;
        private String gender;
        private Double price;
        private UUID brandId;
        private UUID productCategoryId;
        private String imageUrl;
        private UUID warehouseId;
        private Integer stock;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder sku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder name(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be empty.");
            }
            this.name = name;
            return this;
        }

        public Builder gender(String gender) {
            if (gender == null) {
                throw new IllegalArgumentException("Product gender cannot be null.");
            }
            this.gender = gender;
            return this;
        }

        public Builder price(Double price) {
            if (price == null) {
                throw new IllegalArgumentException("Product price cannot be null.");
            }
            this.price = price;
            return this;
        }

        public Builder brandId(UUID brandId) {
            if (brandId == null) {
                throw new IllegalArgumentException("Brand ID cannot be null.");
            }
            this.brandId = brandId;
            return this;
        }

        public Builder productCategoryId(UUID productCategoryId) {
            if (productCategoryId == null) {
                throw new IllegalArgumentException("Product category ID cannot be null.");
            }
            this.productCategoryId = productCategoryId;
            return this;
        }


        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder warehouseId(UUID warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder stock(Integer stock) {
            this.stock = stock;
            return this;
        }
        public Product build() {
            return new Product(this);
        }

    }
}
