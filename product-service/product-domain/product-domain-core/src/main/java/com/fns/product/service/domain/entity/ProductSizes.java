package com.fns.product.service.domain.entity;

import java.util.UUID;

// Entity: ProductSizes
public class ProductSizes {
    private final UUID id; // Unique ID for the size
    private String size; // Size description (e.g., "S", "M", "L")
    private Boolean isStock; // Availability flag

    // Constructor for creating a new ProductSizes instance
    public ProductSizes(String size, Boolean isStock) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be null or empty.");
        }
        if (isStock == null) {
            throw new IllegalArgumentException("Stock status cannot be null.");
        }

        this.id = UUID.randomUUID();
        this.size = size;
        this.isStock = isStock;
    }

    // Private constructor for the Builder pattern
    private ProductSizes(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.size = builder.size;
        this.isStock = builder.isStock;
    }

    // Static factory method for the Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public Boolean getIsStock() {
        return isStock;
    }

    // Setters for updatable fields
    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be null or empty.");
        }
        this.size = size;
    }

    public void setIsStock(Boolean isStock) {
        if (isStock == null) {
            throw new IllegalArgumentException("Stock status cannot be null.");
        }
        this.isStock = isStock;
    }

    @Override
    public String toString() {
        return "ProductSizes{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", isStock=" + isStock +
                '}';
    }

    // Builder Class
    public static class Builder {
        private UUID id;
        private String size;
        private Boolean isStock = true; // Default value for stock status

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder size(String size) {
            if (size == null || size.trim().isEmpty()) {
                throw new IllegalArgumentException("Size cannot be null or empty.");
            }
            this.size = size;
            return this;
        }

        public Builder isStock(Boolean isStock) {
            if (isStock == null) {
                throw new IllegalArgumentException("Stock status cannot be null.");
            }
            this.isStock = isStock;
            return this;
        }

        public ProductSizes build() {
            if (this.size == null || this.size.trim().isEmpty()) {
                throw new IllegalArgumentException("Size must be set.");
            }
            return new ProductSizes(this);
        }
    }
}
