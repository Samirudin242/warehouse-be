package com.fns.product.service.domain.entity;

import java.util.UUID;

// Entity: ProductImages
public class ProductImages {
    private final UUID id; // Unique ID for the image
    private final UUID productId; // Associated Product ID
    private String imageUrl; // URL of the product image

    // Constructor for creating a new ProductImages instance
    public ProductImages(UUID productId, String imageUrl) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null.");
        }
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty.");
        }

        this.id = UUID.randomUUID();
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    // Private constructor for the Builder pattern
    private ProductImages(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.productId = builder.productId;
        this.imageUrl = builder.imageUrl;
    }

    // Static factory method for the Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Setters for updatable fields
    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty.");
        }
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ProductImages{" +
                "id=" + id +
                ", productId=" + productId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    // Builder Class
    public static class Builder {
        private UUID id;
        private UUID productId;
        private String imageUrl;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder productId(UUID productId) {
            if (productId == null) {
                throw new IllegalArgumentException("Product ID cannot be null.");
            }
            this.productId = productId;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                throw new IllegalArgumentException("Image URL cannot be null or empty.");
            }
            this.imageUrl = imageUrl;
            return this;
        }

        public ProductImages build() {
            if (productId == null) {
                throw new IllegalArgumentException("Product ID must be set.");
            }
            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                throw new IllegalArgumentException("Image URL must be set.");
            }
            return new ProductImages(this);
        }
    }
}
