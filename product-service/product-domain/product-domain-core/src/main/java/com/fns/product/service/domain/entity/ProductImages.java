package com.fns.product.service.domain.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

// Entity: ProductImages
@Slf4j
@Getter
public class ProductImages {
    // Getters
    private final UUID id; // Unique ID for the image
    private final UUID product_id; // Associated Product ID
    private String imageUrl; // URL of the product image

    // Constructor for creating a new ProductImages instance
    public ProductImages(UUID id, UUID product_id, String imageUrl) {
        log.info("Creating ProductImages instance for product url {}", imageUrl);
        if (product_id == null) {
            throw new IllegalArgumentException("Product ID cannot be null.");
        }
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty. 1");
        }

        this.id = id;
        this.product_id = product_id;
        this.imageUrl = imageUrl;
    }

    // Private constructor for the Builder pattern
    private ProductImages(Builder builder) {
        this.id = builder.id;
        this.product_id = builder.product_id;
        this.imageUrl = builder.imageUrl;
    }

    // Static factory method for the Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    // Setters for updatable fields
    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty. 2");
        }
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ProductImages{" +
                "id=" + id +
                ", productId=" + product_id +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    // Builder Class
    public static class Builder {
        private UUID id;
        private UUID product_id;
        private String imageUrl;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder productId(UUID productId) {
            if (productId == null) {
                throw new IllegalArgumentException("Product ID cannot be null.");
            }
            this.product_id = productId;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                throw new IllegalArgumentException("Image URL cannot be null or empty. 3");
            }
            this.imageUrl = imageUrl;
            return this;
        }

        public ProductImages build() {
            if (product_id == null) {
                throw new IllegalArgumentException("Product ID must be set.");
            }
            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                throw new IllegalArgumentException("Image URL must be set.");
            }
            return new ProductImages(this);
        }
    }
}
